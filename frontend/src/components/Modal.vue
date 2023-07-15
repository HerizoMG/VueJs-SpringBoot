<template>
    <div class="modal" v-show="open">
        <div class="modalInside">
            <div>
                <h2>New Client</h2>
            </div>
            <div class="inputForm">
                <form>
                    <input v-model="client.name"
                           required
                           type="text"
                           placeholder="Name">
                    <input v-model="client.balance"
                           required
                           type="number"
                           placeholder="Balance">
                </form>
            </div>
            <div class="inputButton">
                <button v-if="!this.updateModal"  @click="saveClient">Add</button>
                <button v-else @click="updateClient">Update</button>
                <button class="bottonClose" @click="closeModal">Close</button>
            </div>
        </div>
    </div>
</template>

<script>

import ClientDataService from "@/services/ClientDataService";

export default {
    props: {
        clientToEdit: {
            type: Object
        },
        updateModal: {
            type: Boolean
        }
    },
    name: "Modal",
    data() {
        return {
            open : false,
            client: {
                name: "",
                balance: null
            },
        }
    },
    watch: {
        clientToEdit: {
            immediate: true,
            handler(client){
                if (client) {
                    this.client.name = client.nom;
                    this.client.balance = client.solde;
                }
            }
        }
    },
    methods: {
        openModal() {
            this.open = true;
            this.client = {
                name: "",
                balance: null
            };
        },
        closeModal(){
            this.open = false;
            this.updateModal = false;
        },
        saveClient() {
            var data = {
                nom: this.client.name,
                solde: this.client.balance
            };

            ClientDataService.create(data)
                .then(response => {
                    this.open = false;
                    console.log(response.data);
                    this.$emit("clientSubmitted")
                })
                .catch(e => {
                    console.log(e);
                })
                .finally(() => {
                    this.client = {
                        name: "",
                        balance: null
                    };
                });
        },
        updateClient() {
            var data = {
                nom: this.client.name,
                solde: this.client.balance
            };

            ClientDataService.update(this.clientToEdit.numCompte, data)
                .then(response => {
                    this.open = false;
                    console.log(response.data);
                    this.$emit("clientSubmitted")
                })
                .catch(e => {
                    console.log(e);
                })
                .finally(() => {
                    this.client = {
                        name: "",
                        balance: null
                    };
                    this.updateModal = false;
                });
        }
    },
}
</script>

<style scoped>
.modal {
    position: fixed;
    z-index: 999;
    width: 400px;
    height: 500px;
    color: #12f669;
    -webkit-transition: opacity 600ms linear 600ms;
    -moz-transition: opacity 600ms linear 600ms;
    -ms-transition: opacity 600ms linear 600ms;
    -o-transition: opacity 600ms linear 600ms;
    transition: opacity 600ms linear 600ms;
    background: #222429;
    text-align: center;
}

.modalInside {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    height: 100%;
}


.inputForm form input {
    width: 300px;
    height: 30px;
    margin: 20px;
    border: 1px solid #12f669;
    border-radius: 10px;
    background: #222429;
    color: #ffffff;
    font-size: 20px;
    padding: 10px;
}

.inputButton {
    margin-bottom: 50px;
}

.inputButton button {
    margin: 5px 10px;
    color: #ffffff;
}

.bottonClose {
    background-color: #BD5532;
    color: white;
}


.inputForm form{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}
</style>