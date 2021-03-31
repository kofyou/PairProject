import os
import json
class FileOperate:
    def __init__(self,filepath):
        self.__filepath=filepath
        self.__sort=False

    def file_delete(self,filename):
        all_file=os.listdir(self.__filepath)
        for file in all_file:
            all_json=os.listdir(self.__filepath+"/"+file)
            if  all_json.count(filename):
                print("删除")
                os.remove(self.__filepath+"/"+file+"/"+filename)
                return True
        return False
    def file_search(self,filename):
        all_file=os.listdir(self.__filepath)
        results=[]
        for file in all_file:
            all_json=os.listdir(self.__filepath+"/"+file)
            results+=[self.__filepath+"/"+file+"/"+x for x in all_json if x.find(filename)!=-1]
        return results
    def file_sort(self):
        all_file=os.listdir(self.__filepath)
        all_json=[]
        for file in all_file:
            all_json+=os.listdir(self.__filepath+"/"+file)
        if self.__sort:
            all_json.sort()
            self.__sort=False
            return False
        elif not self.__sort:
            all_json.sort(reverse=True)
            self.__sort=True
            return True
    def get_all_json(self):
        all_file=os.listdir(self.__filepath)
        all_json=[]
        for file in all_file:
            all_json+=[self.__filepath+"/"+file+"/"+x for x in os.listdir(self.__filepath+"/"+file)]
        return all_json

    """
    得到一个dict{大会一=>{年份=>数量,年份=>数量},大会二=>{年份=>数量,年份=>数量}}
    """
    def get_dict_meet_year_num(self):
        all_file=os.listdir(self.__filepath)
        dic={"ICCV":{},"ECCV":{},"CVPR":{}}
        for filename in all_file:
            if filename.startswith("ICCV"):
                for x in  os.listdir(self.__filepath+"/"+filename):
                    year=x.split("_")[0]
                    if dic["ICCV"].keys().__contains__(year):
                        dic["ICCV"][year]+=1
                    else:
                        dic["ICCV"][year]=1
            elif filename.startswith("ECCV"):
                for x in  os.listdir(self.__filepath+"/"+filename):
                    year=x.split("_")[0]
                    if dic["ECCV"].keys().__contains__(year):
                        dic["ECCV"][year]+=1
                    else:
                        dic["ECCV"][year]=1
            elif filename.startswith("CVPR"):
                for x in  os.listdir(self.__filepath+"/"+filename):
                    year=x.split("_")[0]
                    if dic["CVPR"].keys().__contains__(year):
                        dic["CVPR"][year]+=1
                    else:
                        dic["CVPR"][year]=1
        return dic