<template>
  <div class="navbar">
      <div class="titleNavBar">
          <h1>VueJS - Spring Boot</h1>
          <div class="backTopLeft"></div>
          <div class="backBottom"></div>
          <div class="backTopRight"></div>
          <div class="backRight"></div>
      </div>
      <div class="inputSearchNavBar">
<!--          <input placeholder="Search ..." type="text">-->
          <div class="search-box">
              <input v-model="keySearched"
                     @keyup="searchClient"
                     type="text"
                     placeholder="Search a client ..."/>
              <button type="reset"></button>
          </div>
      </div>
  </div>
</template>

<script>
export default {
    name: "Navbar",
    data() {
        return {
            keySearched: ''
        }
    },
    methods: {
        searchClient() {
            this.$emit('searchedEmit', this.keySearched);
        }
    }
}
</script>

<style scoped>

.backTopLeft {
    position: absolute;
    z-index: 99999;
    top: 20px;
    --b: 1px;   /* border thickness */
    --s: .7em; /* size of the corner */
    --c: #12f669;

    padding: calc(.05em + var(--s)) calc(.3em + var(--s));
    color: var(--c);
    --_p: var(--s);
    background:
            conic-gradient(from 90deg at var(--b) var(--b),#0000 90deg,var(--c) 0)
            var(--_p) var(--_p)/calc(100% - var(--b) - 2*var(--_p)) calc(100% - var(--b) - 2*var(--_p));
    transition: .3s linear, color 0s, background-color 0s;
    outline: var(--b) solid #0000;
    outline-offset: .1em;
}
.backBottom {
    position: absolute;
    z-index: 99999;
    bottom: 45px;
    height:70px;
    width: 250px;
    --b: 1px;   /* border thickness */
    --s: .7em; /* size of the corner */
    --c: #12f669;

    padding: calc(.05em + var(--s)) calc(.3em + var(--s));
    color: var(--c);
    --_p: var(--s);
    background:
            conic-gradient(from 90deg at var(--b) var(--b), rgba(59, 21, 21, 0) 90deg,var(--c) 0)
            var(--_p) var(--_p)/calc(100% - var(--b) - 2*var(--_p)) calc(100% - var(--b) - 2*var(--_p));
    transition: .3s linear, color 0s, background-color 0s;
    outline: var(--b) solid #0000;
    outline-offset: .1em;
}
.backTopRight {
    width: 100px;
    height: 100px;
    position: absolute;
    z-index: 99999;
    top: 20px;
    --b: 1px;   /* border thickness */
    --s: .7em; /* size of the corner */
    --c: #BD5532;

    padding: calc(.05em + var(--s)) calc(.3em + var(--s));
    color: var(--c);
    --_p: var(--s);
    background:
            conic-gradient(from 90deg at var(--b) var(--b),#0000 90deg,var(--c) 0)
            var(--_p) var(--_p)/calc(100% - var(--b) - 2*var(--_p)) calc(100% - var(--b) - 2*var(--_p));
    transition: .3s linear, color 0s, background-color 0s;
    outline: var(--b) solid #0000;
    outline-offset: .1em;
}
.backRight {
    position: absolute;
    z-index: 99999;
    right: 20px;
    --b: 1px;   /* border thickness */
    --s: .7em; /* size of the corner */
    --c: #BD5532;

    padding: calc(.05em + var(--s)) calc(.3em + var(--s));
    color: var(--c);
    --_p: var(--s);
    background:
            conic-gradient(from 90deg at var(--b) var(--b),#0000 90deg,var(--c) 0)
            var(--_p) var(--_p)/calc(100% - var(--b) - 2*var(--_p)) calc(100% - var(--b) - 2*var(--_p));
    transition: .3s linear, color 0s, background-color 0s;
    outline: var(--b) solid #0000;
    outline-offset: .1em;
}

.search-box {
    font-size: 20px;
    border: solid 1px #000000;
    display: inline-block;
    position: relative;
    border-radius: 2.5em;
}
.search-box input[type=text] {
    font-family: inherit;
    font-weight: bold;
    width: 2.5em;
    height: 2.5em;
    padding: 0.3em 2.1em 0.3em 0.4em;
    border: none;
    box-sizing: border-box;
    border-radius: 2.5em;
    transition: width 800ms cubic-bezier(0.68, -0.55, 0.27, 1.55) 150ms;
}
.search-box input[type=text]:focus {
    outline: none;
    padding-left: 20px;
}
.search-box input[type=text]:focus, .search-box input[type=text]:not(:placeholder-shown) {
    width: 40em;
    transition: width 800ms cubic-bezier(0.68, -0.55, 0.27, 1.55);
}
.search-box input[type=text]:focus + button[type=reset], .search-box input[type=text]:not(:placeholder-shown) + button[type=reset] {
    transform: rotate(-45deg) translateY(0);
    transition: transform 150ms ease-out 800ms;
}
.search-box input[type=text]:focus + button[type=reset]:after, .search-box input[type=text]:not(:placeholder-shown) + button[type=reset]:after {
    opacity: 1;
    transition: top 150ms ease-out 950ms, right 150ms ease-out 950ms, opacity 150ms ease 950ms;
}
.search-box button[type=reset] {
    background-color: transparent;
    width: 1.4em;
    height: 1.4em;
    border: 0;
    padding: 0;
    outline: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0.55em;
    right: 0.55em;
    transform: rotate(-45deg) translateY(2.2em);
    transition: transform 150ms ease-out 150ms;
}


.search-box button[type=reset]:before, .search-box button[type=reset]:after {
    content: "";
    background-color: #ec3a3a;
    width: 0.3em;
    height: 1.4em;
    position: absolute;
}
.search-box button[type=reset]:after {
    transform: rotate(90deg);
    background-color: #ec3a3a;
    opacity: 0;
    transition: transform 150ms ease-out, opacity 150ms ease-out;
}

</style>