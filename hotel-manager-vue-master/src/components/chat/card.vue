<template>
  <div id="card">
    <header>
      <img class="avatar" v-bind:src="user.img" v-bind:alt="user.name">
      <p class="name">{{username}}</p>
    </header>
    <footer>
      <input class="search" type="text" v-model="$store.state.filterKey" placeholder="search user...">
    </footer>
  </div>
</template>

<script>
  import Cookies from 'js-cookie'

  export default {
    name: 'card',
    created: function() {
      this.username = Cookies.get('admin_name')
      // this.userInfo = null
      // this.role = Cookies.get('role')
    },
    data() {
      return {
        username: null,
        user: {
          name: 'Li-yiwei',
          img: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F4d%2Fd6%2Ff0%2F4dd6f0e142a057fba0bf73235c4167a5.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644396483&t=beb2e15aaa8cbdc32f33d601a6aea58d'
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  #card {
    padding: 12px;

    .avatar {
      width: 40px;
      height: 40px;
      vertical-align: middle; /*这个是图片和文字居中对齐*/
    }

    .name {
      display: inline-block;
      padding: 10px;
      margin-bottom: 15px;
      font-size: 16px;
    }

    .search {
      background: #26292E;
      height: 30px;
      line-height: 30px;
      padding: 0 10px;
      border: 1px solid #3a3a3a;
      border-radius: 4px;
      outline: none; /*鼠标点击后不会出现蓝色边框*/
      color: #FFF;
    }
  }
</style>
