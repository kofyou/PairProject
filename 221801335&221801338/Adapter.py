from JsonHelper import *
from fileoperate import *
class AdapterClass:
    def __init__(self,filepath="D:/论文数据"):
        self.__fileoperate=FileOperate(filepath)
        self.__dict={}
        self.__list=[]
        
    def get_key_dic(self)->dict:
        if self.__dict:
            return self.__dict
        _alljson=self.__fileoperate.get_all_json()
        dic={}
        for json in _alljson:
            JsonHelper.jsonfile=json
            lis=JsonHelper.get(field="keywords.kwd")
            for l in lis:
                tmp={json.split("/")[len(json.split("/"))-1],
                    JsonHelper.get("doiLink"),
                    JsonHelper.get("abstract")}
                if dic.keys().__contains__(l):
                    dic[l].append(tmp)
                else:
                    dic[l]=[tmp]
        self.__dict=dic
        return dic
    """
    得到所有的关键字
    """
    def get_keys(self):
        if not self.__dict:
            self.get_key_dic()
        return self.__dict.keys()
    """
    得到对应关键字的文章列表
    """
    def get_key_list(self,key):
        if not self.__dict:
            self.get_key_dic()
        return self.__dict.get(key)

    def get_json_messages(self,name):
        if self.__list:
            return self.__list
        files=self.__fileoperate.file_search(name)
        lis=[]
        for file in files:
            JsonHelper.jsonfile=file
            keys=JsonHelper.get("keywords.kwd")
            links=JsonHelper.get("doiLink")
            abstract=JsonHelper.get("abstract")
            tmplis=[]
            tmplis.append(file.split("/")[len(file.split("/"))-1])
            tmplis.append(keys)
            tmplis.append(links)
            tmplis.append(abstract)
            lis.append(tmplis)
        self.__list=lis
        return lis
    """
    删除对应文件(全名)
    """
    def file_delete(self,filename):
        return self.__fileoperate.file_delete(filename)
    """
    找到文件(模糊)
    """
    def file_search(self,filename):
        return self.__fileoperate.file_search(filename)

    """
    对文件排序 返回true或false
    """
    def file_sort(self):
        return self.__fileoperate.file_sort()

    """
    得到一个dict{大会一=>{年份=>数量,年份=>数量}}
    """
    def get_dict_meet_year_num(self):
        return self.__fileoperate.get_dict_meet_year_num()