<template>
    <div class="containerTable">
        <div class="tableView">
            <table class="rectangle-table">
                <thead>
                <tr>
                    <th style="width: 80px">Number</th>
                    <th>Name</th>
                    <th>Balance</th>
                    <th>Observation</th>
                    <th style="width: 80px; text-align: center">Action</th>
                </tr>
                </thead>
                <tbody v-if="filteredList.length">
                    <tr v-for="client in filteredList" :key="client.numCompte">
                        <td>{{ `C${String(client.numCompte).padStart(3, '0')}` }}</td>
                        <td>{{ client.nom }}</td>
                        <td>{{ client.solde }}</td>
                        <td v-if="getObservation(client.solde) == 'Insuffisant'" style="background-color: rgba(217,186,4,0.56)">{{ this.observation }}</td>
                        <td v-if="getObservation(client.solde) == 'Moyen'" style="background-color: rgba(80,189,50,0.42)">{{ this.observation }}</td>
                        <td v-if="getObservation(client.solde) == 'Elevé'" style="background-color: rgba(204,13,13,0.4)">{{ this.observation }}</td>
                        <td class="btnAction">
                            <svg @click="openEditModal(client)" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 20 16"><g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round">
                                <path fill="#4ef542" d="m7.5 9l-3 .54L5 6.5L10.73.79a1 1 0 0 1 1.42 0l1.06 1.06a1 1 0 0 1 0 1.42Z"/><path d="M12 9.5v3a1 1 0 0 1-1 1H1.5a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1h3"/></g>
                            </svg>
                            <svg @click="deleteClient(client.numCompte)" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                <path fill="#ff0e84" d="M7 21q-.825 0-1.413-.588T5 19V6H4V4h5V3h6v1h5v2h-1v13q0 .825-.588 1.413T17 21H7ZM17 6H7v13h10V6ZM9 17h2V8H9v9Zm4 0h2V8h-2v9ZM7 6v13V6Z"/>
                            </svg>
                        </td>
                    </tr>
                </tbody>
                <tbody v-else>
                    <tr>
                        <td colspan="5" style="text-align: center">No data available</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <Modal ref="modal" :updateModal="updating" @clientSubmitted="this.getAllClients()" :clientToEdit="clientTmpToEdit" />
        <div class="buttonAdd">
            <button @click="openModal" type="submit">Ajouter</button>
        </div>
    </div>
    <div class="balanceView">
        <div>Solde Minimal: {{ this.balance.min }} </div>
        <div>Solde Maxmal:  {{ this.balance.max }} </div>
        <div>Solde Total:   {{ this.balance.total }} </div>
    </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import ClientDataService from "@/services/ClientDataService";

export default {
    name: "Table",
    props: {
        searchKeyProps: String
    },
    components: {Modal},
    data()  {
        return {
            clients: [],
            updating: false,
            clientTmpToEdit: [],
            observation: "",
            filterData: [],
            balance: {
                min: null,
                max: null,
                total: null
            }
        }
    },
    computed: {
        // Filter the list of clients
        filteredList() {
            return this.clients.filter(client => {
                return client.nom.toLowerCase().includes(this.searchKeyProps.toLowerCase());
            });
        }
    },
    methods: {
        openModal() {
            this.$refs.modal.openModal();
            this.updating = false;
        },
        getAllClients(){
            ClientDataService.getAll()
                .then(res => {
                    const balance = [];
                    this.clients = res.data;
                    this.clients.forEach(client => {
                        balance.push(client.solde);
                    });
                    this.balance.min = Math.min(...balance);
                    this.balance.max = Math.max(...balance);
                    this.balance.total = balance.reduce((a, b) => a + b, 0);
                })
                .catch(ex => {
                    console.log(ex);
                });
        },
        getObservation(obs){
            if(obs < 1000){
                this.observation = "Insuffisant";
            }else if(obs <= 5000){
                this.observation = "Moyen";
            }else{
                this.observation = "Elevé";
            }
            return this.observation;
        },
        deleteClient(id){
            ClientDataService.delete(id)
                .then(res => {
                    console.log(res);
                    this.getAllClients();
                })
                .catch(ex => {
                    console.log(ex)
                })
        },
        openEditModal(client){
            this.clientTmpToEdit = { ...client };
            this.$refs.modal.openModal();
            this.updating = true;
        }
    },
    mounted() {
        this.getAllClients();
    }

}
</script>

<style scoped>

</style>