"""
  相关配置信息
"""

class Config(object):
    SQLALCHEMY_DATABASE_URI = "mysql+mysqlconnector://%s:%s@%s/%s" % ('root', 'HRYYCFLqswslhk1', '127.0.0.1', 'paper')
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    DEBUG = True
    SECRET_KEY = '123456'
    # REDIS_HOST = "127.0.0.1"
    # REDIS_PORT = 6379