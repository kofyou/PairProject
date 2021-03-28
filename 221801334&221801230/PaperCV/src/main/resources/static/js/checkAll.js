function checkAllYear(){
    var checkAllEle = document.getElementById("checkAllYear");
    if(checkAllEle.checked == true){
        var checkOnes = document.getElementsByName("checkOneYear");
        for(var i = 0; i < checkOnes.length; i++){
            checkOnes[i].checked = true;
        }
    }else{
        var checkOnes = document.getElementsByName("checkOneYear");
        for(var i = 0; i < checkOnes.length; i++){
            checkOnes[i].checked = false;
        }
    }
}

function checkAllMeeting(){
    var checkAllEle = document.getElementById("checkAllMeeting");
    if(checkAllEle.checked == true){
        var checkOnes = document.getElementsByName("checkOneMeeting");
        for(var i = 0; i < checkOnes.length; i++){
            checkOnes[i].checked = true;
        }
    }else{
        var checkOnes = document.getElementsByName("checkOneMeeting");
        for(var i = 0; i < checkOnes.length; i++){
            checkOnes[i].checked = false;
        }
    }
}