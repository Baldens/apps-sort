var textFile = "";
var isFile = false;

window.onload = function() {
    var buttonUploadFile = document.getElementById('button-upload-file');

    buttonUploadFile.addEventListener('change', function(e) {
        file = buttonUploadFile.files[0];
        loadFile(file);
        isFile = true;
    });

    var buttonSort = document.getElementById('button-sort');
    buttonSort.addEventListener('click', function(){
        if( isFile ){

              sortAjaxRequest();
          } else {
              alert("Загрузи файл сначала!")
          }

    });
}

function loadFile(){
    var separatorSymbol = document.querySelector('.content-block_controller_select-symbol-array').value;

    var reader = new FileReader();
    reader.onload = function(e) {
        var text = reader.result;

        var words = text.split(separatorSymbol);
        appendToBlock('leftList', words);
    }

    reader.readAsText(file);
}

function appendToBlock(tableId, words) {
    var table = document.getElementById(tableId);

    var resultHtml = "";
    table.innerHTML = "";
    for (var i = 0; i < words.length; i++) {
        var stringNoSpace = words[i].replace(/\s*/g, '');
        textFile += stringNoSpace + " ";

        resultHtml += `<div class='line'>${words[i]}</div>`;
    }

    table.innerHTML = resultHtml;
}

function sortAjaxRequest(){
    var dataJson = '{"typeSort": "' + document.querySelector('.content-block_controller_select-type-sort').value + '", "text": "' + textFile + '"}';

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/sort",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data: dataJson,
        success: function(dataIn){
            var newData = dataIn.text;
            $('#rightList').empty();
            var sortArray = newData.split(" ");
            for(var i = 0; i < sortArray.length; i++){
                $('#rightList').append($('<div class="line">' + sortArray[i] + '</div>'));
            }
        },
        error: function(data){
           alert("json error");
        }
    });
}


