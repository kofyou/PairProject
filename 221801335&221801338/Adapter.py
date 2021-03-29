from JsonHelper import *
from fileoperate import *
class AdapterClass:
    def __init__(self,filepath="D:/论文数据"):
        self.__fileoperate=FileOperate(filepath)
        self.__dict={}
    
    def get_key_dic(self)->dict:
        if self.__dict:
            return self.__dict
        _alljson=self.__fileoperate.get_all_json()
        dic={}
        for json in _alljson:
            JsonHelper.jsonfile=json
            lis=JsonHelper.get(field="keywords.kwd")
            for l in lis:
                if dic.keys().__contains__(l):
                    dic[l].append(json)
                else:
                    dic[l]=[json]
        self.__dict=dic
        return dic
    def get_keys(self):
        if not self.__dict:
            self.get_key_dic()
        return self.__dict.keys()
    def get_key_list(self,key):
        if not self.__dict:
            self.get_key_dic()
        return self.__dict.get(key)
