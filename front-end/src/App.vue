<template>
  <div class="calculator">
    <div class="screen">
      <div class="input">
        Ahmed
      </div>
      <div class="result">
        Ahmed
      </div>

    </div>
    <br>

    <div class="buttons">
      <button class="operator">%</button>
      <button class="function">CE</button>
      <button class="function">C</button>
      <!--Backspace-->
      <button class="backspace">&#x232B</button> 
      <button class="inverse"><sup>1</sup>&frasl;<sub>x</sub></button>
      <button class="square">x<sup>2</sup></button> 
      <button class="root">&#8730<span style="text-decoration: overline;">x</span></button> 
      <button class="operator">÷</button> 
      <button class="number">7</button>
      <button class="number">8</button>
      <button class="number">9</button>
      <button class="number">×</button>
      <button class="number">4</button>
      <button class="number">5</button>
      <button class="number">6</button>
      <button class="operator">-</button>
      <button class="number">1</button>
      <button class="number">2</button>
      <button class="number">3</button>
      <button class="operator">+</button>
      <button class="number"><sup>+</sup>&frasl;<sub>-</sub></button>
      <button class="number">0</button>
      <button class="number">.</button>
      <button class="operator">=</button>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 40px;
}

div .calculator {
  background-color: #444178;
  align-items: center;
  height: 80vh;
  width: 53vh;
  margin-left: 20%; /*was 40% */
  border: 2px solid black;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  position: absolute;
  padding: 50px 20px 25px 20px; /*top right bottom left */
}

div .buttons {
  position: relative;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  /*gap: 0px;*/
  flex-wrap: wrap;
}
.calculator .buttons button{
  cursor: pointer;
  font-size: 25px;
  margin: 5px;
  border: 1px solid rgb(42, 42, 42);
  box-shadow: 0 0 1.5px rgba(72, 55, 132, 0.5);
  background-color: #53687E;
  color: white;
  border-radius: 18px;
  text-align: center;
  padding: 15px 30px
}
.calculator .buttons button:hover{
  background-color: #787878;
}
.calculator .buttons button:active{
  background-color: #333333;
}

.screen {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  background-color: rgb(255, 255, 255); /* Transparent background */
  padding: 34px 15px;
  text-align: left;
}

.input, .result {
  margin-bottom: 5px;
  margin-top: 8px;
}
.result {
  color: black;
  font-size: 31px;
}
.input {
  font-size: 20px;
  color :#626060;
}
</style>
