import struct
import json
import os

def read_int(file):
    return struct.unpack('<I', file.read(4))[0]

def read_str(file):
    s = b''
    bytes_read = 0
    while True:
        b = file.read(1)
        bytes_read += 1
        if b == b'\x00':
            break
        s += b
    return s.decode('utf-8'), bytes_read

def decode_lang_file(lang : str):

    file = open(f'text-assets/{lang}.utf8', 'rb')

    crc_seed = read_int(file)
    entry_count = read_int(file)

    entries = []
    for _ in range(entry_count):
        key_hash = read_int(file)
        value_offset = read_int(file)
        entries.append((key_hash, value_offset))

    decoded = {}

    cur_offset = 0
    for key_hash, value_offset in entries:

        skip = value_offset - cur_offset
        if skip != 0:
            file.read(skip)
            cur_offset += skip
        
        s, skip = read_str(file)
        cur_offset += skip
        
        decoded[key_hash] = s
    
    json_data = {
        'seed': crc_seed,
        'data': decoded
    }

    if not os.path.exists('decoded-text'):
        os.mkdir('decoded-text')
    with open(f'decoded-text/{lang}.json', 'w') as out:
        out.write(json.dumps(json_data, ensure_ascii=False))

for file_name in os.listdir('text-assets'):
    decode_lang_file(file_name[:file_name.index('.utf8')])