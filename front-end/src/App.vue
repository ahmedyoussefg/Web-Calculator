<template>
  <div class="calculator" @keyup="handleKeyPress" ref="calculatorContainer" tabindex="0">
    <div class="screen">
      <div class="input">
        {{ current_input.replaceAll("*","×").replaceAll("/","÷") }}
      </div>
      <div class="result">
        {{ computed_result }}
      </div>

    </div>
    <br>

    <div class="buttons">
      <button class="operator" @click="handleOperator($event)">%</button>
      <button class="function" @click="clearScreen">CE</button>
      <button class="function" @click="clearScreen">C</button>
      <!--Backspace-->
      <button class="backspace" @click="deleteLast">&#x232B;</button> 
      <button class="inverse" @click="inverse"><sup>1</sup>&frasl;<sub>x</sub></button>
      <button class="square" @click="square">x<sup>2</sup></button> 
      <button class="root" @click="sqrt">&#8730;<span style="text-decoration: overline;">x</span></button> 
      <button class="operator" @click="handleOperator($event)">÷</button> 
      <button class="number" @click ="handleNumberButton($event)">7</button>
      <button class="number" @click ="handleNumberButton($event)">8</button>
      <button class="number" @click ="handleNumberButton($event)">9</button>
      <button class="number" @click ="handleOperator($event)">×</button>
      <button class="number" @click ="handleNumberButton($event)">4</button>
      <button class="number" @click ="handleNumberButton($event)">5</button>
      <button class="number" @click ="handleNumberButton($event)">6</button>
      <button class="operator" @click="handleOperator($event)">-</button>
      <button class="number" @click ="handleNumberButton($event)">1</button>
      <button class="number" @click ="handleNumberButton($event)">2</button>
      <button class="number" @click ="handleNumberButton($event)">3</button>
      <button class="operator" @click="handleOperator($event)">+</button>
      <button class="plusminus" @click="handlePlusMinus"><sup>+</sup>&frasl;<sub>-</sub></button>
      <button class="number" @click="handleNumberButton($event)">0</button>
      <button class="number" @click ="handleDecimalClick">.</button>
      <button class="equal" @click="handleEqualsClick">=</button>
    </div>
  </div>
</template>

<script>

export default {
  data(){
    return{
      current_input:'',
      computed_result:''
    };
  },
  methods: {
    clearScreen(){
      this.current_input='';
      this.computed_result='';
    },
    handleOperator(event){
      let operator=event.target.textContent;
      if(operator==='÷')
        operator ='/';
      else if(operator ==='×')
        operator = '*';
      if(this.current_input==='')
        return;
      if(this.current_input==='-'){
        return;
      }
      const last = this.current_input.slice(-1);
      if (last == '.'){
        this.current_input+="0"+operator;
        return;
      }
      if(this.isOperator(last)){
        this.current_input = this.current_input.slice(0, -1) + operator;
      }
      else
      {
        if (operator==='%'){
          this.current_input+="/100";
          this.evaluateExpression();
        }
        else
          this.current_input+=operator;
      }
    },
    handleDecimalClick(){
      if(this.current_input===''){
        this.current_input+="0.";
        return;
      }
      const last = this.current_input.slice(-1);
      if(this.isOperator(last)){
        this.current_input+="0.";
      }
      else{
        this.current_input+=".";
      }
    },
    handleNumberButton(event){
      const num = event.target.textContent;
      this.current_input+=num;
      this.evaluateExpression();
    },
    sqrt(){
      if(this.current_input===''){
        return;
      }
      const last = this.current_input.slice(-1);
      if (this.isOperator(last)){
        return;
      }
      else if(last=='.'){
        this.current_input+="0^0.5";
      }
      else
      {
        this.current_input+="^0.5";
      }
      this.evaluateExpression();
    },
    square(){
      if(this.current_input===''){
        return;
      }
      const last = this.current_input.slice(-1);
      if (this.isOperator(last)){
        return;
      }
      else if(last=='.'){
        this.current_input+="0^2";
      }
      else
      {
        this.current_input+="^2";
      }
      this.evaluateExpression();

    },
    inverse(){
      if(this.current_input===''){
        return;
      }
      const last = this.current_input.slice(-1);
      if(this.isOperator(last))
        return;
      let myinput=this.current_input;
      for (let i=myinput.length-1;i>=0;i--){
        if(this.isOperator(myinput.charAt(i))){
          let last_str = myinput.slice(0,i+1);
          last_str+="1/"
          myinput=myinput.slice(-(myinput.length-i-1))
          this.current_input=last_str+myinput;
          this.evaluateExpression();
          return;
        }
      }
      this.current_input="1/"+this.current_input;
      this.evaluateExpression();
      return;
    },
    deleteLast(){
      if(this.current_input!=''){
        this.current_input=this.current_input.slice(0,-1);
      }
      if (this.current_input==''){
        this.computed_result='';
        return;
      }
      const last = this.current_input.slice(-1);
      if (!this.isOperator(last)){
        this.evaluateExpression();
      }
      else
      {
        // nothing
      }
      /**
       * if last character is not operator, send expression,
       *  else do nothing
       */
    },
    isOperator(ch){
      if(ch==='+'||ch=='-'||ch=='/'||ch=='*'){
        return true;
      }
      else
      {
        return false;
      }
    },
    handleEqualsClick(){
      // already sent
      if(this.isOperator(this.current_input.slice(-1))){
        this.computed_result='E';
        return;
      }
      this.current_input=this.computed_result;
    },
    handlePlusMinus(){
      if(this.current_input===''){
        this.current_input+="-";
        return;
      }
      if (this.current_input==='-'){
        this.current_input='';
        return;
      }
      let myinput = this.current_input;
      for (let i=myinput.length-1;i>=0;i--){
        let curr=myinput.charAt(i);
        if(this.isOperator(curr)){
          if(curr==='+'){
            //myinput[i]='-';
            this.current_input=myinput.slice(0, i) + "-" + myinput.slice(i+1);
          }
          else if (curr==='-'){
           // myinput[i]='+';
            this.current_input=myinput.slice(0, i) + "+" + myinput.slice(i+1);
          }
          else
          {
            continue;
          }
          this.evaluateExpression();
          return;
        }
      }
      this.current_input="-"+this.current_input;
      this.evaluateExpression();
      return;
    },
    evaluateExpression(){
      fetch(`http://localhost:8081/calculate/?expression=${encodeURIComponent(this.current_input)}`)
      .then(res=>{
        if (res.ok){
          return res.text();
        }
        else
        {
          console.log('error');
        }
      })
      .then(result=>{this.computed_result=result; console.log("Result " +result);})
      .catch(error=>{console.log(error)});
    },
    handleKeyPress(event) {
      // Get the key that was pressed
      const key = event.key;
      this.$refs.calculatorContainer.focus();
      // Handle numeric keys
      if (/[0-9]/.test(key)) {
        this.handleNumberButton({ target: { textContent: key } });
      }

      // Handle operator keys
      if (/[+\-*/^]/.test(key)) {
        this.handleOperator({ target: { textContent: key } });
      }

      // Handle decimal point
      if (key === '.') {
        this.handleDecimalClick();
      }

      // Handle equals key
      if (key === 'Enter') {
        this.handleEqualsClick();
      }

      // Handle backspace key
      if (key === 'Backspace') {
        this.deleteLast();
      }
      if (key === 'Escape'){
        this.clearScreen();
      }
    },
  },
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
  margin-right: 30%;
  margin-left:40%;
}

div .calculator {
  background-color: #444178;
  align-items: center;
  border: 2px solid black;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  display: absolute;
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
  border-radius: 20px;
  word-wrap: break-word;
}

.input, .result {
  margin-bottom: 5px;
  margin-top: 8px;
}
.result {
  color: black;
  font-size: 31px;
  min-height: 35px;
}
.input {
  font-size: 20px;
  color :#626060;
  min-height: 24px;
}
.calculator:focus{
  outline: none;
}
</style>
