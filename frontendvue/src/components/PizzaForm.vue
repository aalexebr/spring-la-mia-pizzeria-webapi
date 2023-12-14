<template>
    <div class="column align-middle">
        <button @click="$emit('back')">back</button>
        <template v-if="updatePizza != null">
            <h2>
                updating pizza: {{ updatePizza.name }}
            </h2>
        </template>
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
            <div>
                <template v-for="ing in ingredients" :key="ing.id">
                    <input type="checkbox" :name="ing.id" :id="ing.id" :value="ing.id" v-model="formData.ingredientId">
                    <label :for="ing.id">{{ ing.name }}</label>
                </template>
                
            </div>
            <button type="submit">submit</button>
        </form>
    </div>
    
</template>
<script setup>
    // import
    import { onMounted, ref,defineProps } from 'vue';

    import axios from 'axios';

    // data
    const ingredients = ref(null);
    const formData = ref({
        name: "",
        price:null,
        description:"",
        url:"",
        ingredientId:[]
    });
    const updatePizza = props.pizza;

    const getIngrededientIdArray = ()=>{
        let ingArray = [];
        if(props.pizza.ingredients.length > 0){
            for(let i=0;i <props.pizza.ingredients.length; i++){
                ingArray.push(props.pizza.ingredients[i].id);
            }
        }
        return ingArray;
    }

    if(updatePizza != null){
        formData.value.name = updatePizza.name
        formData.value.price = updatePizza.price
        formData.value.description = updatePizza.description
        formData.value.url = updatePizza.url
        formData.value.ingredientId = getIngrededientIdArray();
    }

    // console.log(updatePizza)

    // emit
    const emits = defineEmits(["back"]);

    // methods
    const submit = async()=>{
        // console.log(formData.value);
        if(updatePizza == null){
            const data = await axios.post(
            "http://localhost:8080/api/create", 
            formData.value
            );
            // console.log("data", data);
            emits("back");
        }
        else{
            const data = await axios.put(
            `http://localhost:8080/api/update/${props.pizza.id}`, 
            formData.value
            );
            // console.log("data", data);
            emits("back");
        }
        
    }

    const getIngredients = async()=>{
        const data = await axios.get("http://localhost:8080/api/ingredients/index");
        ingredients.value = data.data
        // console.log(data.data)
    }
    // props
    const props = defineProps({
        pizza: {
            type: Object,
            required: false
        }
    });


    // mounted
    onMounted(getIngredients);
</script>

<style lang="scss" scoped>
@use "../assets/scss/main" as *;
input[type=checkbox] {
    display: inline-block;
}
</style>