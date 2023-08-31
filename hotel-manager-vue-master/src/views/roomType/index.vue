<template>
  <el-card class="box-card" shadow="always">
    <div slot="header">
      <el-button type="primary" @click="navigateTo('add')">添加房间类型</el-button>
      <el-button type="danger" @click="deleteRoomTypeByBatch(ids)">批量删除</el-button>
      <el-input
        style="width: 300px;position: absolute;right: 150px;"
        placeholder="输入房间类型进行搜索"
        prefix-icon="el-icon-search"
        v-model="search"
        clearable
      >
      </el-input>
      <el-button type="primary" icon="el-icon-search" style="float: right;" @click="fetchData">搜索</el-button>
    </div>
    <el-table
      ref="multipleTable"
      :loading="listLoading"
      :data="list"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"

        width="30"/>
      <el-table-column
        prop="typeId"
        sortable
        width="60"
        label="编号"/>
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
        prop="price"
        width="100"
        sortable
        label="预定价格"/>

      <el-table-column
        prop="discount"
        label="预定折扣"/>
      <el-table-column
        prop="rest"
        label="空闲房数"/>
      <el-table-column

        prop="area"
        label="房间面积">
        <template slot-scope="scope">
          <span>{{ scope.row.area }} m²</span>
        </template>
      </el-table-column>

      <el-table-column prop="cover" label="图片">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px;position: center"
            :src="scope.row.cover"
            :preview-src-list="[scope.row.cover]">
          </el-image>
        </template>
      </el-table-column>

      <el-table-column
        prop="window"
        label="是否有窗">
        <template slot-scope="scope">
          <span>{{ scope.row.windows | hasWindow }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="床位数量">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top-end">
            <p>床大小: {{ scope.row.bedSize }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.bedNum }}</el-tag>
            </div>
          </el-popover>
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
      <el-table-column label="操作">
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
  import { deleteBatchRoomType, getAllRoomType, delRoomType } from '@/api/roomType'

  export default {
    filters: {
      hasWindow: function(value) {
        if (value > 0) {
          return '有'
        } else {
          return '无'
        }
      }
    },
    data() {
      return {
        total: 10,//总条数
        currentPage: 1,//当前页
        pageSize: 5,
        search: '',
        visible2: false,
        multipleSelection: [],
        listLoading: true,
        list: null,
        loading: false,
        ids: []
      }
    },
    created: function() {
      this.fetchData()
    },
    methods: {
      deleteRoomTypeByBatch(rows) {
        if (rows.length !== 0) {
          this.$confirm('是否确认删除这' + rows.length + '条数据?', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function() {
            return deleteBatchRoomType(rows)
          }).then(() => {
            this.fetchData()
          })
        } else {
          // this.msgWarning('未选择数据！')
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
      },
      handleCurrentChange(pageNum) {  // 改变当前页码触发
        this.currentPage = pageNum
        this.fetchData()
      },

      fetchData() {
        this.listLoading = true
        getAllRoomType(this.currentPage, this.pageSize, this.search).then(response => {
          console.log(response)
          this.list = response.data.list
          this.list.reverse()
          this.total = response.data.total
          this.listLoading = false
        })
      },
      navigateTo(val) {
        this.$router.push({ path: '/roomType/' + val })
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row)
          })
        } else {
          this.$refs.multipleTable.clearSelection()
        }
      },
      handleEdit(index, row) {
        this.$router.push({
          path: '/roomType',
          name: 'EditType',
          params: {
            id: row.typeId
          }
        })
      },
      handleDel(row) {
        row.visible2 = false
        row.loading = true
        delRoomType(row.typeId).then(response => {
          const res = response
          if (res.code === 1000) {
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.list.push()
            this.fetchData()
          } else {
            this.$message({
              message: '删除失败！',
              type: 'error'
            })
          }
        })

        row.loading = false

      },
      handleSelectionChange(val) {
        console.log(val + '---------------')
        this.ids = val.map(v => v.typeId)   // [{id,name}, {id,name}] => [id,id]
        console.log(this.ids)
      },

      massDeletion() {
        this.multipleSelection.forEach(function(value, index) {
          console.log(value.typeId)
        })
      }
    }
  }
</script>

<style>
  .red {
    color: red;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }
</style>
