<template>
  
</template>

<script>
import axios from "axios";
import { response } from "express";

const URL_PIZZE = "http://localhost:8080/api/1/pizze"

export default {
    name: 'Pizza',

    data(){
        return{
            
        };
    },

    methods: {

    },

    created(){
        axios.get( URL_PIZZE + '/all')
        .then((response) => {
            console.log(response);
        })
    }
}
</script>

<style>

</style>