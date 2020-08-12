#!/usr/bin/python
# -*- coding: utf-8 -*-
import os
import io
from werkzeug.utils import secure_filename
import sys
sys.path.append('/home/njh/anaconda3/lib/python3.7/site-packages')
sys.path.append('/home/njh/anaconda3/lib/python3.7/site-packages/tika')

import PyPDF2
import tika
tika.initVM()
from tika import parser, detector
import argparse
import commands
from flask import Flask, jsonify, request, render_template, redirect, url_for, send_from_directory
allow_extensions = set(['pdf'])
app = Flask(__name__)
UPLOAD_DIR = './uploads/'
app.config['UPLOAD_DIR'] = UPLOAD_DIR
@app.route('/', methods=['GET'])
def aaa():
    html = "<h1>Na Ji Hae</h1>"
    html+='<li><a href="/upload" title="upload">02.file_upload</a></li>'
    return html

#업로드 HTML 렌더링
@app.route('/upload')
def render_file():
   return render_template('upload.html')

@app.route('/fileUpload', methods = ['GET', 'POST'])
def upload_file():
   if request.method == 'POST':
       file = request.files['file']
       fname = file.filename.encode("utf-8")
       path = os.path.join(app.config['UPLOAD_DIR'], fname)
       file.save(path)
       ##텍스트 파일 추출##
       file2 = io.open(os.path.splitext("./uploads/"+fname)[0]+".txt", "w", encoding='utf-8')
       parsed = tika.parser.from_file("./uploads/"+fname)
       text=parsed['content']
       file2.writelines(text)
       file2.close()
       ##사진 추출##
   return "./uploads/"+fname+'uploads 디렉토리 -> 파일 업로드 성공!'

if __name__ == '__main__':
    try:
        parser = argparse.ArgumentParser(description="")
        parser.add_argument('--listen-port',  type=str, required=True, help='REST service listen port')
        args = parser.parse_args()
        listen_port = args.listen_port
    except Exception, e:
        print('Error: %s' % str(e))
    ipaddr=commands.getoutput("hostname -I").split()[0]
    print "Starting the service with ip_addr="+ipaddr
    app.run(debug=True,host=ipaddr,port=int(listen_port),threaded=True)
