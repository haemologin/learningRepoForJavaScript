var list = document.querySelectorAll("ul");
var p = document.querySelector("p");
var input = document.getElementById("target");

$("#target").keypress(function(event){
    if(event.which === 13){
        event.preventDefault();
        event.stopPropagation();
        addListItem(this);
    }
    input = $(this).val();
});

document.getElementById("addItemButton").onclick = function (){
    if(input == ""){}
    else{
        addListItem($("#target"));   
    }
}

function addListItemB(){
    addListItem($("#target"));
}

function addListItem(item){
    var newItem = $(item).val(); 
    if(newItem == ""){}
    else{
        $("#firstUl").append("<li><span><i class='fa fa-trash'></i></span>" + newItem +"</li>");
        $(item).val('');
        p.textContent = Number(p.textContent) + 1;
    }
    
}

$("#firstUl").on("click","span",function(event){
    $(this).parent().fadeOut(500, function(){
        $(this).remove();
    });
    p.textContent = Number(p.textContent) - 1;
    event.stopPropagation();
})

$("#firstUl").on("click","li",function(){
    $(this).toggleClass("selected");
    if(this.classList.contains("selected")){
        jQuery(this).children("span").css({
            marginRight: "20px",
            height: "40px",
            textAlign: "center",
            backgroundColor: "rgb(255, 49, 49)",
            display: "inline-block",
            color: "white",
            width: "40px",
            transition: "0.2s linear",
            opacity: "1.0"
        })
    }else {
        jQuery(this).children("span").css({
            marginRight: "20px",
            height: "40px",
            textAlign: "center",
            backgroundColor: "rgb(255, 49, 49)",
            display: "inline-block",
            color: "white",
            width: "0",
            transition: "0.2s linear",
            opacity: "0"
        })
    }
})

$("h2").on("click", function(){
    //jQuery(this).children("#dropdownContent").css({display: "block"});
    jQuery("#tipUl").toggleClass("dropdownContent");
    jQuery(this).toggleClass("h2Active");
})



