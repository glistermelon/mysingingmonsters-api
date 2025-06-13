package com.glisterbyte.Utility;

public class Link {
    String link;
    String hostname;
    String uri;
    public Link(String link) {
        this.link = link;
        int begin = link.indexOf("//") + 2;
        int uriIndex = link.indexOf('/', begin);
        hostname = link.substring(begin, uriIndex);
        uri = link.substring(uriIndex);
    }
    public String getLink() {
        return link;
    }
    public String getHostname() {
        return hostname;
    }
    public String getUri() {
        return uri;
    }
}
