document.getElementById("dice").addEventListener('click', main)

function main() {
    if(redPlayer.chance==true) {
        return redFunc()
    }if(bluePlayer.chance==true) {
        return blueFunc()
    } if(greenPlayer.chance==true) {
        return greenFunc()
    } if(yellowPlayer.chance==true) {
        return yellowFunc()
    }
}
function redFunc() {
    if(redPlayer.chance == true){
        diceThrow(redPlayer)
        document.getElementById("redCount").textContent = redPlayer.count;
    }
    if(redPlayer.chance==false){
        bluePlayer.chance = true;
        console.log(bluePlayer.chance)
    }
}
function greenFunc() {
    if(greenPlayer.chance == true){
        diceThrow(greenPlayer)
        document.getElementById("greenCount").textContent = greenPlayer.count
    }if (greenPlayer.chance == false){
        redPlayer.chance = true;
    }
}
function yellowFunc() {
    if(yellowPlayer.chance == true) {
        diceThrow(yellowPlayer)
        document.getElementById("yellowCount").textContent = yellowPlayer.count
    }if(yellowPlayer.chance == false){
        greenPlayer.chance = true
    }
}
function blueFunc() {
    if(bluePlayer.chance==true) {
        diceThrow(bluePlayer)
        document.getElementById("blueCount").textContent = bluePlayer.count
    }if (bluePlayer.chance==false){
        yellowPlayer.chance=true
    }
}

function diceThrow(color){
    let diceValue = Math.ceil(Math.random()*Math.ceil(6));
    let html = diceStyle(diceValue);
    document.getElementById("diceSection").innerHTML = html
    document.getElementById("chance").innerHTML = `${color.name} just rolled!`
    console.log(diceValue, color.name)
    if(diceValue==6){
        start = true;
        color.flag = true;
        color.chance = true;
        color.sixCount++
        document.getElementById("message").innerHTML = `<div>Whoa!  ${color.name} got a six! Roll the dice again</div>`
    }else{
        color.chance = false;
        document.getElementById("message").innerHTML = ""
    }
    if(color.count<52){
        if(color.flag){
            if((diceValue==6)&&(color.sixCount==1)){
                color.count = 0
            }else{
                color.count = color.count + diceValue;
            }
        }
    }else{
        let checkwin = color.count + diceValue;
        if(checkwin<57) {
            color.count = color.count + diceValue;
        }

        if(checkwin==57){
            document.getElementById("message").innerHTML = `<div class="winwin"><p>Congratulations! ${color.name} has won.</p></div>`
        }
    }
    return color.count

}

function diceStyle(num){
    // let url = `https://www.wpclipart.com/recreation/games/dice/die_face_${num}.png`
    let url = `https://www.clipartmax.com/middle/m2i8Z5H7G6m2Z5K9_1-dice-face-${num}-png/`
    let obj = `
        <img class"dice1" src="${url}"/>
    `;
    return obj
}
function Player(name, flag, count, sixCount, chance){
    this.name = name
    this.flag = flag;
    this.count = count;
    this.sixCount = sixCount;
    this.chance = chance
}

let redPlayer = new Player("Red", false, 0, 0, true)
let bluePlayer = new Player("Blue", false, 0, 0, false)
let yellowPlayer = new Player( "Yellow", false, 0, 0, false)
let greenPlayer = new Player("Green", false, 0, 0, false)
