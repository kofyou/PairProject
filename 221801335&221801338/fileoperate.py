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
                os.remove(self.__filepath+"/"+filename)
                return True
        return False
    def file_search(self,filename):
        all_file=os.listdir(self.__filepath)
        results=[]
        for file in all_file:
            all_json=os.listdir(self.__filepath+"/"+file)
            results+=[x for x in all_json if x.find(filename)!=-1]
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