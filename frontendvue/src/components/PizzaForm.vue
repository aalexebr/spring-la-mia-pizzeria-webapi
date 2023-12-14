<template>
    <button @click="$emit('back')">back</button>
    <form @submit.prevent="submit">
        <div>
            <label for="name">insert name</label>
            <input type="text" v-model="formData.name" required>
        </div>
        <div>
            <label for="name">insert price</label>
            <input type="number" v-model="formData.price" step="0.01" min="0.01" required>
        </div>
        <div>
            <label for="name">insert desc</label>
            <input type="text" v-model="formData.description">
        </div>
        <div>
            <label for="name">insert url of image</label>
            <input type="text" v-model="formData.url">
        </div>
        <button type="submit">submit</button>
    </form>
</template>
<script setup>
    // import
    import { onMounted, ref,defineProps } from 'vue';

    import axios from 'axios';

    // data
    const formData = ref({
        name: "",
        price:null,
        description:"",
        url:""
    });
    const updatePizza = props.pizza;
    if(updatePizza != null){
        formData.value.name = updatePizza.name
        formData.value.price = updatePizza.price
        formData.value.description = updatePizza.description
        formData.value.url = updatePizza.url
    }

    // console.log(updatePizza)

    // emit
    const emits = defineEmits(["back"]);

    // methods
    const submit = async()=>{
        if(updatePizza == null){
            const data = await axios.post(
            "http://localhost:8080/api/create", 
            formData.value
            );
            console.log("data", data);
            emits("back");
        }
        else{
            const data = await axios.put(
            `http://localhost:8080/api/update/${props.pizza.id}`, 
            formData.value
            );
            console.log("data", data);
            emits("back");
        }
        
    }

    const props = defineProps({
        pizza: {
            type: Object,
            required: false
        }
    });
</script>