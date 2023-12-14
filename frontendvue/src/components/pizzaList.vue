<template>
    <template v-if="pizzaList != null">
        <div class="column align-middle">
            <div>
               <label for="search">search by name</label>
                <input type="text" v-model="searchName" id="search"> 
            </div>
            <div>
                <h1>
                    Pizzas:
                </h1>
                <ul v-if="pizzaList.length > 0 ">
                    <template v-for="(p, index) in pizzaList" :key="index">
                        <li v-if="p.name.includes(searchName)"
                            @click="$emit('pizzaIndexShow', index)">
                            {{ p.name }}
                            <!-- {{ index }} -->
                        </li>
                    </template>
                    
                </ul>
            </div>
            
            
        </div>
	</template>
</template>
<script setup>
    // import
    import { defineProps, ref  } from 'vue';  

    // data
    let searchName = ref("");

    // props
    const props = defineProps({
        pizzaList: {
            type: Array,
            required: true
        }
    });

    // emit
    const emits = defineEmits(["pizzaIndexShow"]);
</script>

<style lang="scss">
@use "../assets/scss/main" as *;
@use "../assets/scss/12bool" as *;

li{
    cursor: pointer;
    &:hover{
        text-decoration: underline;
    }
}

</style>