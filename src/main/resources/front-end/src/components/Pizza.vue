<template>
    <h1>Pizze</h1>

    <div class="d-flex">

        <div v-for="pizza in pizze" :key="pizza.id" class="card">

            <!-- card for pizza -->
            <div v-if="pizza_id_edit != pizza.id">
                <h3>{{ pizza.name }}</h3>
                <p>Description: {{ pizza.description }}</p>
                <h5>Price: {{ pizza.price }}</h5>
                <button class="btn btn-modify" @click="pizzaEdit(pizza.id)">Edit</button>
                <button class="btn btn-cancel">Delete</button>
            </div>
            <!-- card for pizza -->
            
            <!-- form for update -->
            <div v-else>
                <form @submit="pizzaUpdate">
                    <div class="margin-form">
                        <label for="name">Name</label>
                        <input type="text" name="name" v-model="pizza.name">
                    </div>

                    <div class="margin-form">
                        <label for="description">Description</label>
                        <input type="text" name="description" v-model="pizza.description">
                    </div>
                    
                    <div class="margin-form">
                        <label for="price">Price</label>
                        <input type="number" name="price" v-model="pizza.price">
                    </div>
                    <input class="btn btn-submit" type="submit" value="UPDATE">
                    <button class="btn btn-cancel" @click="editPizza(PIZZA_ID_EDIT)">CANCEL</button>
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

        getPizza(id){

            for (let index = 0; index < this.pizze.length; index++) {

                const pizza = this.pizze[index];
                
                if(pizza.id == id){
                    return index;
                }
            }
            return -1;
        },

        getPizzaIndex(id) {
            return this.pizze[this.getPizzaIndexById(id)];
        },

        pizzaUpdate(e){
            e.preventDefault();

            const id = this.pizza_id_edit;
            const pizza = this.getPizza(id);

            this.pizzaEdit(PIZZA_ID_EDIT);

            axios.post(URL_PIZZE + "/update/" + id, pizza)
            .then((response) => {

                const index = this.getPizzaIndex(id);
                // const oldPizza = this.pizze[index];
                const pizza = res.data;
                
                this.pizze[index] = pizza;

            })
            .catch((error) => {
                console.log(error);
            })
        }
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

.margin-form{
    margin: 10px 0px;
}

.btn{
    padding: 5px 10px;
    margin: 0px 5px 10px 5px;
}

.btn-modify{
    background-color: gold;
    border-color: gold;
    border-radius: 5px;
}

.btn-submit{
    background-color: green;
    border-color: green;
    color: white;
    border-radius: 5px;
}

.btn-cancel{
    background-color: crimson;
    border-color: crimson;
    color: white;
    border-radius: 5px;
}

</style>