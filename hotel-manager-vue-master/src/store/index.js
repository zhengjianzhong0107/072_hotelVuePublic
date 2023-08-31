import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import getters from './getters'
import { getOperator } from '../api/worker'

Vue.use(Vuex)
const now = new Date()
const store = new Vuex.Store({
  modules: {
    app,
    user
  },
  getters,
  state: {
    sessions: [],
    operators: [],
    currentSessionId: -1,
    filterKey: ''
  },

  mutations: {
    changeCurrentSessionId(state, id) {
      state.currentSessionId = id
    },
    addMessage(state, msg) {
      state.sessions[state.currentSessionId - 1].messages.push({
        content: msg,
        date: new Date(),
        self: true
      })
    },
    INIT_DATA(state) {
      //浏览器历史聊天记录
      /*let data = localStorage.getItem('vue-wechat-session');
      console.log(data)
      if (data) {
        state.sessions = JSON.parse(data);
      }*/

    },
    INIT_OPERATOR(state, data) {
      state.operators = data
    }
  },
  actions: {
    initData(context) {
      getOperator('').then(result => {
        console.log(result)
        if(result){
          context.commit('INIT_OPERATOR',result)
        }
      })

    }
  }

})
store.watch(function(state) {
  return state.sessions
}, function(val) {
  console.log('CHANGE: ', val)
  localStorage.setItem('vue-wechat-session', JSON.stringify(val))
}, {
  deep: true/*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})
export default store
