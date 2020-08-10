
import re
from konlpy.tag import Kkma
from konlpy.utils import pprint
import kss

def clean_text(text):
    #cleaned_text = re.sub('[a-zA-z]','',text)
    cleaned_text = re.sub('[\{\}\[\]\/;:|\)*~`^\-_+<>@\#$%&\\\=\(\'\"\♥\♡\ㅋ\ㅠ\ㅜ\ㄱ\ㅎ\ㄲ\ㅡ]','',text)
    return cleaned_text

def no_space(text):
    pattern1=re.compile('\n\n')
    text=re.sub(pattern1, " ", text)
    pattern2=re.compile('\n')
    text=re.sub(pattern2, "",text)
    #text=re.sub('check','\n',text)
    return text

#-*- encoding:utf-8
from tika import parser
PDFfileName = '출판사를 위한 전자책 길잡이.pdf'
parsed = parser.from_file(PDFfileName)
fileOut = open('fileOut.txt', 'w', encoding='utf-8')
text=no_space(parsed['content'])
text=clean_text(text)
#print(parsed['content'], file=fileOut)
#print(text,file=fileOut)
for sent in kss.split_sentences(text):
    print(sent,file=fileOut)
fileOut.close()