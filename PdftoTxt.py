import re

def no_space(text):
    text1= re.sub('\n|\t|\r','',text)
    text2=re.sub('\n\n','',text1)
    return text2

#-*- encoding:utf-8
from tika import parser
PDFfileName = 'test_kor.pdf'
parsed = parser.from_file(PDFfileName)
#print(parsed["content"])
fileOut = open('Out.txt', 'w', encoding='utf-8')
text=no_space(parsed['content'])
#print(parsed['content'], file=fileOut)
print(text,file=fileOut)
fileOut.close()
