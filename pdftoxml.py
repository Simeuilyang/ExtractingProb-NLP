
import re
from konlpy.tag import Kkma
from konlpy.utils import pprint
import kss

#-*- encoding:utf-8
from tika import parser
path='/Users/han-eunju/Documents/textRank/ExtractingProb-NLP-1/'
PDFfileName = path + 'test1.pdf'
parsed = parser.from_file(PDFfileName, xmlContent=True)
fileOut = open('fileOut.xml', 'w', encoding='utf-8')
print(parsed['content'], file=fileOut)
fileOut.close()

from bs4 import BeautifulSoup
with open("fileOut.xml", "r", encoding="utf-8") as b:
    inputxml = b.read()
soup = BeautifulSoup(inputxml, "xml") 

def remove_header(text):
    start_i=-1
    end_i=-1
    for idx,i in enumerate(text):
        if(i=='\n' and start_i!=-1):
            end_i=idx
            break
        elif(i=='\n'):
            start_i=idx
    if(bool(re.search(r'\d',text[start_i:end_i]))==False):
        return text
    else:
        return (text[0:start_i] + text[end_i:len(text)] )


def no_space(text):
    pattern1=re.compile('\n')
    text=re.sub(pattern1, "", text)
    return text

fileOut = open('fileOut.txt', 'w', encoding='utf-8')
paragraph_list = soup.find_all('div',{'class':'page'})
tot_content=''
for a in paragraph_list:
    text=remove_header(a.get_text())
    text=no_space(text)
    tot_content=tot_content + text

for sent in kss.split_sentences(tot_content):
    print(sent,file=fileOut)
#print(tot_content,file=fileOut)

#<p>tag 첫번째가 숫자를 포함하면, 첫번째 <p> tag remove
fileOut.close