import json
class JsonHelper:
    
    jsonfile=""
    @classmethod
    def get(cls, field: str):
        dic={}
        with open(cls.jsonfile,'r',encoding='utf-8') as f:
            dic=json.loads(f.read().strip(';'))
        ret = cls.__get_json_node(dic, field)
 
        if not ret:
            return []
        else:
            return ret
    @classmethod
    def set(cls, field: str, value):
        dic={}
        with open(cls.jsonfile,'r',encoding='utf-8') as f:
            dic=json.loads(f.read().strip(';'))
        ret = cls.__set_json_node(dic, field, value)
        if ret is None:
            raise Exception("field:{} is not exist".format(field))
        return cls
    @classmethod
    def __get_one_json_node(cls, data: dict, field: str):
        if isinstance(data, list):
            for it in data:
                ret = cls.__get_one_json_node(it, field)
                if ret is not None:
                    return ret
 
        if not isinstance(data, dict):
            return None
        if field in data.keys():
            return data[field]
 
        for it in data.keys():
            ret = cls.__get_one_json_node(data[it], field)
            if ret:
                return ret
        return None
    @classmethod
    def __get_json_node(cls, data: dict, field: str):
        node_path = field.split(".")
        node = data
        find = False
        for it in node_path:
            node = cls.__get_one_json_node(node, it)
            if not node:
                return None
            else:
                find = True
 
        if find:
            return node
        else:
            return None
    @classmethod
    def __set_one_json_node(cls, data: dict, field: str, value):
        if isinstance(data, list):
            for it in data:
                ret = cls.__set_one_json_node(it, field, value)
                if ret is not None:
                    return ret
 
        if not isinstance(data, dict):
            return None
        if field in data.keys():
            data[field] = value
            return data
 
        for it in data.keys():
            ret = cls.__set_one_json_node(data[it], field, value)
            if ret:
                return ret
        return None
    @classmethod
    def __set_json_node(cls, data: dict, field: str, value):
        pos = field.find('.')
        if pos != -1:
            parent = field[0:pos]
            node = cls.__get_json_node(data, parent)
            if node is None:
                return None
            else:
                return cls.__set_one_json_node(node, field[pos + 1:], value)
        else:
            return cls.__set_one_json_node(data, field, value)