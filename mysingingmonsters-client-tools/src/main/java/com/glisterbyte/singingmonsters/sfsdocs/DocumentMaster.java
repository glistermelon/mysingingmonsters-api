package com.glisterbyte.singingmonsters.sfsdocs;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.*;
import com.glisterbyte.singingmonsters.sfsmodels.events.GetFriendsResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.MultiUpdateMonsterResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.MultiUpdateMonsterRequest;
import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Stream;

public class DocumentMaster {

    private final Map<Class<?>, Document> docMap = new HashMap<>();

    private final List<Class<? extends SfsRequestModel>> undocumentedRequests = new ArrayList<>();
    private final List<Class<? extends SfsEventModel>> undocumentedEvents = new ArrayList<>();
    private final List<Class<? extends SfsModel>> undocumentedData = new ArrayList<>();

    private final List<Class<? extends SfsModel>> allClasses = new ArrayList<>();

    private final Map<Class<? extends SfsRequestModel>, Class<? extends SfsEventModel>> requestToResponseMap
            = new HashMap<>();

    private final Map<Class<? extends SfsModel>, List<Document>> dataRefMap = new HashMap<>();

    public String getCommand(Class<?> targetClass) {
        return Optional.ofNullable(targetClass.getAnnotation(SfsCmd.class)).map(SfsCmd::value).orElse(null);
    }

    public Document getDocument(Class<?> targetClass) {
        return docMap.get(targetClass);
    }

    public void addDataReference(Class<? extends SfsModel> dataClass, Document doc) {
        dataRefMap.computeIfAbsent(dataClass, x -> new ArrayList<>()).add(doc);
    }

    public List<Document> getDataReferencingDocs(Class<? extends SfsModel> dataClass) {
        return dataRefMap.computeIfAbsent(dataClass, x -> new ArrayList<>());
    }

    public Class<? extends SfsModel> getClassForSimpleName(String simpleName) {
        return Stream.of(allClasses)
                .flatMap(Collection::stream)
                .filter(c -> c.getSimpleName().equals(simpleName))
                .findFirst().orElse(null);
    }

    private void findClasses() {

        Reflections reflections = new Reflections("com.glisterbyte.singingmonsters");

        undocumentedRequests.addAll(reflections.getSubTypesOf(SfsRequestModel.class));
        undocumentedRequests.remove(DbRequestModel.class);

        undocumentedEvents.addAll(reflections.getSubTypesOf(SfsEventModel.class));
        undocumentedEvents.remove(SfsResultResponse.class);
        undocumentedEvents.remove(SfsCorrelatedResultResponse.class);
        undocumentedEvents.remove(DbResponse.class);
        undocumentedEvents.remove(SfsChunkedDbResponse.class);
        undocumentedEvents.remove(GetFriendsResponse.class);

        undocumentedData.addAll(reflections.getSubTypesOf(SfsModel.class));
        undocumentedData.remove(SfsRequestModel.class);
        undocumentedData.removeAll(reflections.getSubTypesOf(SfsRequestModel.class));
        undocumentedData.remove(SfsEventModel.class);
        undocumentedData.removeAll(reflections.getSubTypesOf(SfsEventModel.class));

        allClasses.addAll(undocumentedRequests);
        allClasses.addAll(undocumentedEvents);
        allClasses.addAll(undocumentedData);

        if (
                new HashSet<>(allClasses).size() !=
                        undocumentedRequests.size() + undocumentedEvents.size() + undocumentedData.size()
        ) {
            for (var c : allClasses) {
                if (allClasses.stream().filter(c::equals).count() != 1) {
                    throw new RuntimeException("Duplicate: " + c);
                }
            }
            throw new RuntimeException("Duplicate (unknown somehow)");
        }

        for (var requestClass : undocumentedRequests) {

            String command = getCommand(requestClass);

            docMap.put(requestClass, new Document(this, "commands", command));

            try {
                Class<? extends SfsEventModel> responseClass;
                if (requestClass == MultiUpdateMonsterRequest.class) {
                    responseClass = MultiUpdateMonsterResponse.class;
                }
                else {
                    responseClass = SfsCmdResponseRegistry.getCmdResponseType(command);
                }
                requestToResponseMap.put(requestClass, responseClass);
                undocumentedEvents.remove(responseClass);
            }
            catch (MissingCommandException ignored) { }

        }

        for (var eventClass : undocumentedEvents) {
            String command = getCommand(eventClass);
            docMap.put(eventClass, new Document(this, "events", command));
        }

        for (var dataClass : undocumentedData) {
            String title = DocumentWriter.makeDataTile(dataClass);
            String fileName = title.replace(" ", "");
            Document doc = new Document(this, "data", fileName);
            doc.setTitle(title);
            docMap.put(dataClass, doc);
        }

    }

    public List<Document> documentEverything() {

        findClasses();

        List<Document> docs = new ArrayList<>();

        while (!undocumentedRequests.isEmpty()) {
            var requestClass = undocumentedRequests.removeLast();
            var responseClass = requestToResponseMap.getOrDefault(requestClass, null);
            Document doc = docMap.get(requestClass);
            new DocumentWriter(doc).documentCommand(requestClass, responseClass);
            docs.add(doc);
        }

        while (!undocumentedData.isEmpty()) {
            var dataClass = undocumentedData.removeLast();
            Document doc = docMap.get(dataClass);
            new DocumentWriter(doc).documentData(dataClass);
            docs.add(doc);
        }

        while (!undocumentedEvents.isEmpty()) {
            var eventClass = undocumentedEvents.removeLast();
            Document doc = docMap.get(eventClass);
            new DocumentWriter(doc).documentEvent(eventClass);
            docs.add(doc);
        }

        return docs;

    }

}