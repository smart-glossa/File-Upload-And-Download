$(document).on("click","#signup",function(key) {
        var request = new FormData();                  
        request.append('file', $('#profile')[0].files[0]);
        $.ajax({
            url : url,
            type : 'POST',
            data : request,
            processData : false,
            contentType : false
        }).done(function(result) {
            result = JSON.parse(result);
            if (result.status == 1) {
                alert("Added SuccessFully");

        
            } else {
                alert("Error caused: " + result.message);
            }

        }).fail(function(result) {
            console.log(result);
        });
    });