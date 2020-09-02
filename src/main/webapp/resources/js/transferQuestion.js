function changeToList(str1, str2)
{

    console.log(str1);
    console.log(str2);
    
	var sentence_array = str1.split('\n');
    var word_array = str2.split('\n');
    var question = [];
    var answer = [];
    
    var answer_index = 0;
    
    console.log(sentence_array);
    console.log(word_array);
    
    for(var i = 0; i<word_array.length; i++)
    {
    	if(word_array[i].length == 0)
		{
			word_array.splice(i, 1);
		}
    }
    
    for(var i = 0; i<sentence_array.length; i++)
    {
    	if(sentence_array[i].length == 0)
		{
			sentence_array.splice(i, 1);
		}
    }
    
    console.log(sentence_array);
    console.log(word_array);
    
    var sen = sentence_array[36];
	var wor = word_array[word_array.length-1];
	wor=wor.slice(0,-1);

	console.log(wor);
	console.log(sen.indexOf(wor));
	
	
    for(var i = 0; i<sentence_array.length; i++)
    {
    	//console.log(sentence_array[i]);
    	var tmp_arr = [];
    	var answer_index2= 0;
    	var flag = 0;
    	
    	for(var j = 0; j<word_array.length; j++)
    	{
    		//console.log(word_array[j]);
    		var wor=word_array[j];
    		wor=wor.slice(0,-1);
    		console.log(wor)
    		var n = sentence_array[i].indexOf(wor);
    		
//    		if(i == 36)
//    		{
//    			console.log(sentence_array[i]);
//    			console.log(word_array[j]);s
//    			console.log(n);
//    		}
    			
            if(n != -1){
//                console.log(array[i].length)
//                for(var k=0; k<word_array[j].length; k++){
//                    //console.log(n+j)
//                    sentence_array[i] = sentence_array[i].substr(0, n+k) + "_" + sentence_array[i].substr(n+k+1);
//                }
            	sentence_array[i] = sentence_array[i].substr(0, n) + "_" + sentence_array[i].substr(n+word_array[j].length);
            	
            	tmp_arr.push(word_array[j]);                
                flag = 1;
                //sessionStorage.setItem("answer_"+(answer_index++)+"_"+(answer_index2++), word_array[j])
            }
            
     	}
    	if(flag == 1)
    	{
    		question.push(sentence_array[i]);
    		answer.push(tmp_arr);

    	}
}

    //sessionStorage.setItem("answer", JSON.stringify(answer));
    console.log(question);
    console.log(answer);
    
    var dummyData = new Object();
    dummyData.question = question;
    dummyData.answer = answer;
    
    console.log(dummyData);
    
    return dummyData;
//    for (var i=0;i<array.length;i++){
//        console.log(array[i])
//        var n = str1.indexOf(array[i])
//        //console.log(n)
//        if(n != -1){
//            //console.log(array[i].length)
//            for(var j=0;j<array[i].length;j++){
//                //console.log(n+j)
//                str1 = str1.substr(0, n+j) + "_" + str1.substr(n+j+1);
//            }
//        }
//        console.log(str1);
//    }
    
    //document.getElementById('txtOutput').value=str1;
}

function processFile(file1, file2) {
    var reader1 = new FileReader();
    var reader2 = new FileReader();
    var result;
    
    reader1.readAsText(file1, "UTF-8");
    reader2.readAsText(file2, "UTF-8");
    
    reader1.onload = function () {
        reader2.onload = function(){
        	result = changeToList(reader1.result, reader2.result);
        }
    };
    
    return result;
}

function readTextFile(file, file2) 
{ 
    var allText = null;
    var allText2 = null;
    var rawFile = new XMLHttpRequest(); 
    var rawFile2 = new XMLHttpRequest();
    rawFile.open("GET", file, false); 
    rawFile2.open("GET",file2, false);
    var result;
    
    rawFile.onreadystatechange = function () 
    { 
        if(rawFile.readyState === 4) 
        { 
            if(rawFile.status === 200 || rawFile.status == 0) 
            { 
                allText = rawFile.responseText; 
                //alert(allText); 
                if(allText2 != null)
                {
                    result = changeToList(allText, allText2);

                }
            } 
        } 
    }; 
    rawFile2.onreadystatechange = function()
    {
        if(rawFile2.readyState ===4)
        {
            if(rawFile2.status ===200 || rawFile2.status == 0)
            {
                allText2 = rawFile2.responseText;
                if(allText != null)
                {
                    result = changeToList(allText, allText2);
                }
            }
        }
    }
    rawFile.send(null); 
    rawFile2.send(null);
    
    console.log(result);
    
    return result;
}