
import re
from konlpy.tag import Kkma
from konlpy.utils import pprint
import kss

#-*- encoding:utf-8
from tika import parser
PDFfileName = '출판사를 위한 전자책 길잡이.pdf'
parsed = parser.from_file(PDFfileName, xmlContent=True)
fileOut = open('fileOut.xml', 'w', encoding='utf-8')
print(parsed['content'], file=fileOut)

from bs4 import BeautifulSoup

soup = BeautifulSoup(open(fileOut), 'xml')
#paragraph_list=soup.findAll('div',{"class"="page"})

for a in paragraph_list:
    print a.get_text()

fileOut.close()