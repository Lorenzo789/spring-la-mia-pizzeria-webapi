<template>
    <h1>Pizze</h1>

    <div class="d-flex">

        <div v-for="pizza in pizze" :key="pizza.id" class="card">

            <!-- card for pizza -->
            <div v-if="pizza_id_edit != pizza.id">
                <h3>{{ pizza.name }}</h3>
                <p>Description: {{ pizza.description }}</p>
                <h5>Price: {{ pizza.price }}</h5>
                <button @click="pizzaEdit(pizza.id)">Edit</button>
                <button>Delete</button>
            </div>
            <!-- card for pizza -->
            
            <!-- form for update -->
            <div v-else>
                <form action="">

                </form>
            </div>
            <!-- form for update -->
            
        </div>

    </div>

</template>

<script>
import axios from "axios";

const URL_PIZZE = "http://localhost:8080/api/1/pizze";
const PIZZA_ID_EDIT = -1;

export default {
    name: 'Pizza',

    data(){
        return{
            pizze: [],
            pizza_id_edit: PIZZA_ID_EDIT,
        };
    },

    methods: {
        pizzaEdit(id) {
            this.pizza_id_edit = id;
        },
    },

    created(){
        axios.get( URL_PIZZE + '/all')
        .then((response) => {
           this.pizze = response.data;
        })
        .catch((error) =>{
            console.log(error);
        })
    }
}
</script>

<style scoped>
.d-flex{
    display: flex;
    flex-wrap: wrap;
    padding: 0px 5px 10px 5px;
}

.card{
    width: calc(100% / 3 - 30px);
    margin: 10px;
    border: 1px solid black;
    text-align: center;
}

h1{
    text-align: center;
}

</style>
