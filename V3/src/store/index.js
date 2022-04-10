import { createStore } from 'vuex'
export default createStore({
    state: {
        token: '',
        role:'',
        referees:'',
        oid:'',
        uid:'',
        active:0
    },
    mutations: {
        SET_ACTIVE: (state,index) =>{
            state.active = index
        },
        SET_UID: (state,uid) =>{
            state.uid = uid
        },
        SET_OID: (state,oid) =>{
          state.oid = oid
        },
        SET_REFEREES: (state, referees) => {
            state.referees = referees
        },
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        SET_ROLE: (state, role) => {
            state.role = role
        },
    },
    actions: {},

})
