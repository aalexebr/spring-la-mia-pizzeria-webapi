<template>
  	<pizza-list @pizzaIndexShow="getListIndex"
  	:pizzaList="pizzas"
  	v-if="pizzas != null && !openSinglePizza && !openCreateFlag"/>

	<single-pizza :singlePizza="selectedPizza"
		@back="closeComponents"
		@update="updateSinglePizza"
		v-if="selectedPizza != null && openSinglePizza"/>
	<button v-if="!openCreateFlag && !openSinglePizza"
		@click="openForm">create pizza</button>
	<pizza-form v-if="openCreateFlag || pizzaToUpdate != null"
		:pizza="pizzaToUpdate"
		@back="closeComponents"/>
</template>
<script setup>
// import
import { onMounted, ref } from 'vue';
import axios from 'axios';
// components
import PizzaList from './components/pizzaList.vue';
import SinglePizza from './components/SinglePizza.vue';
import PizzaForm from './components/PizzaForm.vue';

// data
let pizzas = ref(null);
// let pizzaIndex = ref(null);
let selectedPizza = ref(null);
let openSinglePizza = ref(false);
let openCreateFlag = ref(false);
let pizzaToUpdate = ref(null);

// methods
const getListIndex = (index)=>{
	// pizzaIndex.value = index;
	selectedPizza.value = pizzas.value[index];
	// console.log(selectedPizza.value);
	openSinglePizza.value = true;
}

const closeComponents = ()=>{
	openSinglePizza.value = false;
	pizzaToUpdate.value = null;
	openCreateFlag.value = false;
	loadPizzas();
}
const openForm = ()=>{
	openCreateFlag.value = true;
}

const updateSinglePizza = ()=>{
	pizzaToUpdate.value = selectedPizza.value;
	// console.log(pizzaToUpdate.value)
}

// const updateSinglePizza =(id)=>{
// 	console.log(id)
// 	pizzas.value.forEach((pizza) => {
//     if (pizza.id === id) {
// 		pizzaToUpdate.value = pizza;
// 		console.log(pizzaToUpdate.value);
// 		return
//     }
// 	console.log("running")
//   });
// }

const loadPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/index");
  pizzas.value = data.data;
//   console.log("pizz",pizzas);
};

// mounted
onMounted(loadPizzas);

</script>

<style lang="scss" scoped>
@use "./assets/scss/main" as *;

</style>

