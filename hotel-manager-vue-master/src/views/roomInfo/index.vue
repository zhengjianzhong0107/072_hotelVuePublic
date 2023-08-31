<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <el-button type="primary" v-if="user.role==='admin'" @click="navigateTo('add')">添加房间信息</el-button>
      <el-button type="danger" v-if="user.role==='admin'" @click="BatchDelete(ids)">批量删除</el-button>

      <el-input
        style="width: 300px;position: absolute;right: 150px;"
        placeholder="输入房间号进行搜索"
        prefix-icon="el-icon-search"
        v-model="search"
        clearable
      >
      </el-input>
      <el-button type="primary" icon="el-icon-search" style="float: right;" @click="fetchData">搜索</el-button>
    </div>
    <el-table
      ref="multipleTable"
      :data="list"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">

      <el-table-column
        type="selection"/>

      <el-table-column
        prop="roomId"
        label="编号"

        width="50"/>
      <el-table-column
        label="房间号码">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>详细描述: </p>
            <p>{{ scope.row.remark }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.roomNumber }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="房间类型">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>详细描述: </p>
            <p>{{ scope.row.remark }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.roomType }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="roomPrice"
        sortable
        label="价格"/>
      <el-table-column
        label="折扣">
        <template slot-scope="scope">
          <el-tag type="primary">{{ scope.row.roomDiscount + '%' }}</el-tag>
        </template>
      </el-table-column>


      <el-table-column
        prop="roomStatus"
        label="状态">
        <template slot-scope="scope">
          <!--<el-tag :type="showStatus(scope.row.roomStatus)">{{ scope.row.roomStatus }}</el-tag>-->
          <el-button :type="showStatus(scope.row.roomStatus)" size="mini">{{ scope.row.roomStatus | formatStatus }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间">
        <template slot-scope="scope">

          <i class="el-icon-time"/>
          <span style="margin-left: 10px">{{ scope.row.createTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="修改时间">
        <template slot-scope="scope">

          <i class="el-icon-time"/>
          <span style="margin-left: 10px">{{ scope.row.updateTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column
        v-if="user.role==='admin'"
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-popover
            v-model="scope.row.visible2"
            placement="top"
            width="160">
            <p>确定删除吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" round @click="scope.row.visible2 = false">取消</el-button>
              <el-button type="danger" size="mini" round @click="handleDel(scope.row)">确定</el-button>
            </div>
            <el-button slot="reference" :loading="scope.row.loading" size="mini" type="danger"
                       @click="scope.row.visible2 = true">删除
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 14px;">
      <div class="bottom">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>
  </el-card>
</template>

<script>

  import { deleteBatch, getAllRoom, delRoom } from '../../api/roomInfo'
  import { getInfo } from '../../api/login'

  export default {
    filters: {
      formatStatus: function(value) {
        switch (value) {
          case 0:
            return '不可用'
          case 1:
            return '空闲'
          case 2:
            return '已预订'
          case 3:
            return '已入住'
        }
      }
    },
    data() {
      return {

        srcList: [
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F21052112102250D-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1643363420&t=6e3b0e6385abcdefd78c73e900d96b87',
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F21052112102250D-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1643363420&t=6e3b0e6385abcdefd78c73e900d96b87'
        ],

        total: 10,//总条数
        currentPage: 1,//当前页
        pageSize: 5,
        search: '',
        visible2: false,
        multipleSelection: [],
        list: null,
        listLoading: true,
        ids: [],
        user: {},
      }
    },
    created: function() {
      this.fetchData()
    },
    methods: {

      BatchDelete(rows) {
        if (rows.length !== 0) {
          this.$confirm('是否确认删除这' + rows.length + '条数据?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function() {
            return deleteBatch(rows)
          }).then(() => {
            this.fetchData()
          })
        } else {
          this.$confirm('请选择要删除的数据', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          })

        }
      }
      ,
      handleSizeChange(pageSize) {   // 改变当前每页的个数触发
        this.pageSize = pageSize
        this.fetchData()
      }
      ,
      handleCurrentChange(pageNum) {  // 改变当前页码触发
        this.currentPage = pageNum
        this.fetchData()
      }
      ,

      //获取房间所有数据
      fetchData() {
        this.listLoading = true
        getAllRoom(this.currentPage, this.pageSize, this.search).then(response => {
          console.log(response)
          this.list = response.data.list
          this.listLoading = false
          this.total = response.data.total
        })
        //
        getInfo().then(resp => {

          console.log("user--->"+JSON.stringify(resp.data))
          if (resp) {
            this.user = resp.data
            this.user1 = Object.assign({}, this.user)
          }
        })
      }
      ,
      navigateTo(val) {
        this.$router.push({ path: '/roomInfo/' + val })
      }
      ,
      handleEdit(index, row) {
        this.$router.push({
          path: '/roomInfo',
          name: 'EditRoom',
          params: {
            id: row.roomId
          }
        })
      }
      ,
      handleDel(row) {
        row.visible2 = false
        row.loading = true
        delRoom(row.roomId).then(response => {
          const res = response
          if (res.code === 1000) {
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.list.push()
            row.loading = false
            this.fetchData()
          } else {
            this.$message({
              message: '删除失败！',
              type: 'error'
            })
          }
        }).catch(error => {
          row.loading = false
          console.log(error)
        })
        row.loading = false
      }
      ,
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row)
          })
        } else {
          this.$refs.multipleTable.clearSelection()
        }
      }
      ,
      handleSelectionChange(val) {
        console.log(val + '---------------')
        this.ids = val.map(v => v.roomId)   // [{id,name}, {id,name}] => [id,id]
        console.log(this.ids)

      },
      showStatus(val) {
        switch (val) {
          case 0:
            return 'info'
          case 1:
            return 'success'
          case 2:
            return 'warning'
          case 3:
            return 'danger'
        }
      }
    }
  }
</script>

<style>
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }
</style>
