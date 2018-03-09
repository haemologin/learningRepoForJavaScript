var table = document.querySelector(".row1");
var boardElement;
var placeUnitsButton = document.querySelector(".buttonPU");
var element;
var temp;
var hiddenDiv = document.createElement("div");
var isPlaceUnits = false;
var previousElement;
var xy;
var availableUnits = document.querySelectorAll(".unitTypes");
var unitPlacementSelected = false;
var isPushed = false;
var selectedUnit = 0;
var fieldSelected = false;
var counter = 0;
var prevElem;
var firstSelection = true;
var unitName = "";

hiddenDiv.style.backgroundColor = "rgb(0,150,0)";

//board - 10x10 size.
var board = [[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]];
board[9][9] = 0;

//Units for battle - first number is the strength, second is the amount of units can be placed on the board.
var units = {
    tomb:[
    bomb = {strength:11,amount:6,name: "Bomb"},
    marshall = {strength:10,amount:1,name: "Marshall"},
    general = {strength:9,amount:1,name: "General"},
    colonel = {strength:8,amount:2,name: "Colonel"},
    major = {strength:7,amount:3,name: "Major"},
    captain = {strength:6,amount:4,name: "Captain"},
    lieutenant = {strength:5,amount:4,name: "Lieutenant"},
    sergeant = {strength:4,amount:4,name: "Sergeant"},
    miner = {strength:3,amount:5,name: "Miner"},
    scout = {strength:2,amount:8,name: "Scout"},
    spy = {strength:1,amount:1,name: "Spy"},
    flag = {strength:-1,amount:1,name: "Flag"}
    ]
}

var amounts = document.querySelectorAll(".amount");
placeUnitsButton.addEventListener("click", function(){
    placeUnits();
    placeUnits2();
});



init();

function init(){
    for(i = 0; i<amounts.length; i++){
        amounts[i].textContent = units.tomb[i].amount;
    }
    for(i = 0; i<10; i++){
        for(k = 0; k<10; k++){
            var elem = document.createElement("div");
            elem.textContent = "0";
            elem.className = "col-xs-1-10";
            board[i][k]=elem;
            table.appendChild(elem);
        }
    }
    element = document.querySelectorAll(".col-xs-1-10");
    for(i = 60; i<element.length; i++){
        temp = element[i];
        previousElement = element[60];
        temp.addEventListener("click", function(){
            if(isPlaceUnits && this.style.backgroundColor !== hiddenDiv.style.backgroundColor){
                fieldSelected = true;
                if(!unitPlacementSelected){
                    if(!firstSelection){
                        if(Number(amounts[counter].textContent) > 0 && this.textContent != selectedUnitStrength){
                            if(this.textContent != 0){
                                var prevText = this.textContent;
                                this.textContent = selectedUnitStrength;
                                for(n = 0; n<units.tomb.length; n++){
                                    if(prevText == units.tomb[n].strength){
                                        amounts[n].textContent = Number(amounts[n].textContent) + 1;
                                        console.log(n); 
                                    }
                                }
                            }
                            this.textContent = selectedUnitStrength;
                            this.style.backgroundColor = "blue";
                            amounts[counter].textContent = Number(amounts[counter].textContent) -1;
                        }
                    }
                }
                 
                if(previousElement !== this){
                    previousElement.style.backgroundColor = "#42f471";
                    previousElement = this;
                    xy = Array.prototype.indexOf.call(element, this);
                    console.log(xy);
                }
                this.style.backgroundColor = "rgb(0,150,0)"; 
            }else{
                if(this.style.backgroundColor === hiddenDiv.style.backgroundColor && isPlaceUnits){
                    this.style.backgroundColor = "#42f471";
                    fieldSelected = false;
                } 
            }
        });
    }  
}

boardElementSelector(6,4);

function boardElementSelector(x, y){
    var element = board[x][y];
    var elementIndex = (x*10) + y;
    boardElement = document.querySelectorAll(".col-xs-1-10")[elementIndex]; 
}

function placeUnits(){
    isPlaceUnits = true;
    for(i = 0; i<10; i++){
        for(k = 0; k<10; k++){
            if(i>=6){
                board[i][k].style.backgroundColor = "#42f471";
            }
        }
    }
}


function placeUnits2(){
    if(!isPushed){
        
        var temp;
        var tempdiv = document.createElement("div");
        prevElem = availableUnits[0];
        tempdiv.style.color = "white";
        for(i = 0; i < availableUnits.length; i++){
            temp = availableUnits[i];
            temp.addEventListener("click", function(){
                if(tempdiv.style.color !== this.style.color){
                    firstSelection = false;
                    this.style.backgroundColor = "green";
                    this.style.color = "white";
                    counter = Array.prototype.indexOf.call(availableUnits, this);
                    selectedUnit = Number(amounts[counter].textContent);
                    for(j = 0; j<units.tomb.length; j++){
                        unitName = availableUnits[counter].textContent;
                        unitName = unitName.substring(0,unitName.length-2);
                        if(unitName === units.tomb[j].name){
                            selectedUnitStrength = units.tomb[j].strength;
                        }
                    }

                    if(prevElem !== this){
                        prevElem.style.backgroundColor = "white";
                        prevElem.style.color = "black";
                        prevElem = this;
                    }
                    unitPlacementSelected = true;
                }else {
                    this.style.backgroundColor = "white";
                    this.style.color = "black";
                    unitPlacementSelected = false;
                    selectedUnit = 0;
                }
                unitPlacementSelected = false;
            });
        }
        isPushed = true;
    }
}



