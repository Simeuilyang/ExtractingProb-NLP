function changeToList(str1, str2)
{
    var array = str2.split('\n');
    for (var i=0;i<array.length;i++){
        //console.log(array[i])
        var n = str1.indexOf(array[i])
        //console.log(n)
        if(n != -1){
            //console.log(array[i].length)
            for(var j=0;j<array[i].length;j++){
                //console.log(n+j)
                str1 = str1.substr(0, n+j) + "_" + str1.substr(n+j+1);
            }
        }
    }
    console.log(str1)
    document.getElementById('txtOutput').value=str1;
}
function readTextFile(file, file2) 
{ 
    var allText = null;
    var allText2 = null;
    var rawFile = new XMLHttpRequest(); 
    var rawFile2 = new XMLHttpRequest();
    rawFile.open("GET", file, false); 
    rawFile2.open("GET",file2, false);
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
                    changeToList(allText, allText2);

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
                    changeToList(allText, allText2);
                }
            }
        }
    }
    rawFile.send(null); 
    rawFile2.send(null);
}