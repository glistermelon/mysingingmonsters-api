import os
import json

def title_to_camel_case(s : str):
    return s[0].lower() + s[1:].replace(' ', '')

if not os.path.exists('output'):
    os.mkdir('output')

data = json.loads(open('input/structures.json').read())

TAB = ' ' * 4


out = ['// The following is auto-generated via misc/java-info-gen/monsters.py.']

names = []
for structure in data:

    name = title_to_camel_case(structure['name'])
    name = name.replace('\'', '')
    name = name.replace('-', '')
    name = name.replace('(', '')
    name = name.replace(')', '')
    name = name.replace(',', '')
    name = name.replace('.', '')
    name = name.replace('/', 'by')
    name = name.replace('${CAMPAIGN}', 'campaign')

    if name.endswith('AnniversaryMonument'):
        name = 'anniversaryMonument' + name[:name.index('A')]

    if name in names:
        n = 2
        while name + str(n) in names:
            n += 1
        name += str(n)
    names.append(name)

    out.append('')
    out.append(f'public static StructureType {name}() {{')
    out.append(TAB + f'return fromId({structure['id']});')
    out.append('}')

out = '\n'.join(TAB + ln for ln in out)

open('output/structures.java', 'w').write(out)