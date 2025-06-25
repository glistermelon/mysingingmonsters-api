import os
import json
from collections import defaultdict

if not os.path.exists('output'):
    os.mkdir('output')

in_data = json.loads(open('input/monsters.json').read())['monsters_data']

TAB = ' ' * 4

names = []
out_data = defaultdict(dict)

for monster in in_data:

    name = monster['common_name']
    id = monster['monster_id']

    name = name.replace('\'', '')
    name = name.replace('-', '')
    name = name.replace('(', '')
    name = name.replace(')', '')

    name = [s.lower() for s in name.split()]

    if name[0] in [
        'do', 're', 'mi',
        'fa', 'sol', 'la', 'ti'
    ]: name.insert(0, 'dipster')

    island = 'default'

    if not('epic' in name and 'wubbox' in name):

        if name[-1] == 'island':
            island = name[-2]
            name = name[:-2]
        elif len(name) > 2 and name[1] == 'island':
            island = name[0]
            name = name[2:]
        elif name[0] == 'legendary':
            island = 'shugabush'
            name.pop(0)

        if 'wubbox' in name and len(name) > 1:
            if name[-2] == 'fire' and name[-1] == 'haven':
                island = 'fire_haven'
                name = name[:-2]
            elif name[-2] == 'fire' and name[-1] == 'oasis':
                island = 'fire_oasis'
                name = name[:-2]
    
    elif name[0] == 'gold':

        name = name[2:]
        natural_islands = ['plant', 'cold', 'air', 'water', 'earth']
        name[-1] = natural_islands[int(name[-1]) - 1]
        name.append('island')
        island = 'gold'
    
    if island == 'fire':
        island = 'fire_haven'

    name = name[0] + ''.join(s[0].upper() + s[1:] for s in name[1:])
    
    out_data[name][island] = id

out_java = ['// The following is auto-generated via misc/java-info-gen/monsters.py.']
for name in out_data.keys():
    out_java.append('')
    out_java.append(f'public static MonsterSpecies {name}() {{')
    out_java.append(TAB + f'return Cache.getGenericMonsterSpeciesByResourceKey("{name}");')
    out_java.append('}')

out_java = '\n'.join(TAB + ln for ln in out_java)

open('output/monsters.java', 'w').write(out_java)
open('output/monster_types.json', 'w').write(json.dumps(out_data))