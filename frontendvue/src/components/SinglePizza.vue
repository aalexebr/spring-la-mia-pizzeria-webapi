<template>
    <button @click="$emit('back')">back</button>
    <button @click="$emit('update',singlePizza.id)">update</button>
    <h1>pizza:</h1>
    <h2>
        {{ singlePizza.name }}
    </h2>
    <h3>
        price:{{ singlePizza.price }} $
    </h3>
    <p>
        description:
        {{ singlePizza.description }}
    </p>
    <ul v-if="singlePizza.ingredients.length >0">
        ingredients:
        <li v-for="(ing,i) in singlePizza.ingredients">
            {{ ing.name }}
        </li>
    </ul>
    <ul v-if="singlePizza.specialOffer.length >0">
        special offers:
        <li v-for="(off,i) in singlePizza.specialOffer">
            {{ off.startDate }} to {{ off.endDate }}
        </li>
    </ul>
    <button @click="deletePizza">delete</button>
</template>
<script setup>
    // import
    import { defineProps } from 'vue';
    import PizzaForm from './PizzaForm.vue';
    import axios from 'axios';  

    // props
    const props = defineProps({
        singlePizza: {
            type: Object,
            required: true
        }
    });

    // emit
    const emits = defineEmits(["back","update"]);

    // methods

    const deletePizza = async()=>{
        const data = await axios.delete(
            `http://localhost:8080/api/delete/${props.singlePizza.id}`
            );
        console.log("data", data);
        emits("back");
    }

</script>