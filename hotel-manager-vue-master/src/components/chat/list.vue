<template>
  <div id="list">
  	<ul style="padding-left: 0px">
  		<li v-for="item in operators" :class="{ active: item.workerId === currentSessionId }" v-on:click="changeCurrentSessionId(item.id)"><!--   :class="[item.id === currentSessionId ? 'active':'']" -->
  			<img class="avatar"  >
  			<p class="name">{{item.username}}</p>
  		</li>
  	</ul>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'list',
  data () {
    return {

    }
  },
  computed: mapState([
  // 'sessions',
  'operators',
  'currentSessionId'
	]),
  methods:{
  	changeCurrentSessionId:function (id) {
  		this.$store.commit('changeCurrentSessionId',id)
  	}
  }
}
</script>

<style lang="scss" scoped>
#list {
	li {
		padding: 12px 15px;
		border-bottom: 1px solid #292C33;
		cursor: pointer;
		&:hover {
			background-color: rgba(255, 255, 255, 0.03);
		}
	}
  li.active {/*注意这个是.不是冒号:*/
			background-color: rgba(255, 255, 255, 0.1);
	}
	.avatar {
		border-radius: 2px;
		width: 30px;
		height: 30px;
		vertical-align: middle;
	}
	.name {
		display: inline-block;
		margin-left: 15px;
	}
}
</style>
