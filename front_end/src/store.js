import {createStore} from 'vuex'

const store = createStore({
    state() {
        return {
            id: null,
            name: null,
            curModifyNewsId: null,
        }
    },
    mutations: {
        login(state, user) {
            state.id = user.usid
            state.name = user.uname
        },
        logout(state) {
            state.id = null
            state.name = null
        },
        modifyNews(state, id) {
            state.curModifyNewsId = id
        }
    }
})

export default store