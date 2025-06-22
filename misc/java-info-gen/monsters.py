import os
import json

def title_to_camel_case(s : str):
    return s[0].lower() + s[1:].replace(' ', '')

if not os.path.exists('output'):
    os.mkdir('output')

data = json.loads(open('input/monsters.json').read())['monsters_data']

TAB = ' ' * 4


out = ['// The following is auto-generated via misc/java-info-gen/monsters.py.']

names = []
for monster in data:

    name = title_to_camel_case(monster['common_name'])
    name = name.replace('\'', '')
    name = name.replace('-', '')
    name = name.replace('(', '')
    name = name.replace(')', '')

    if name in [
        "do", "re", "mi",
        "fa", "sol", "la", "ti"
    ]: name = "dipster" + name[0].upper() + name[1:]

    if name in names:
        n = 2
        while name + str(n) in names:
            n += 1
        name += str(n)
    names.append(name)

    out.append('')
    out.append(f'public static MonsterType {name}() {{')
    out.append(TAB + f'return fromId({monster['monster_id']});')
    out.append('}')

out = '\n'.join(TAB + ln for ln in out)

open('output/monsters.java', 'w').write(out)