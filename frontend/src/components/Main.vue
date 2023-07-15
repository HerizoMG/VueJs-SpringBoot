<template>
    <Navbar @searchedEmit="handleSearch"/>
    <Table :searchKeyProps="searchKey"/>
    <Footer/>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import Table from "@/components/Table.vue";
import Footer from "@/components/Footer.vue";

export default {
    name: "Main",
    components: {
        Footer,
        Table,
        Navbar,
    },
    data() {
        return {
            searchKey: ''
        }
    },
    methods: {
        handleSearch(searchKey) {
            this.searchKey = searchKey;
            console.log(this.searchKey);
        }
    }
}
</script>

<style scoped>

</style>