<template>
  <div>

    <el-dialog
      title="编辑用户信息"
      :visible.sync="dialogVisible"

      width="30%">
      <div>
        <table>
          <tr>
            <td>姓名：</td>
            <td style="width: 80%">
              <el-input v-model="user1.name"></el-input>
            </td>
          </tr>
          <tr>
            <td>性别：</td>
            <td>
              <el-input v-model="user1.gender"></el-input>
            </td>
          </tr>
          <tr>
            <td>手机号码：</td>
            <td>
              <el-input v-model="user1.phone"></el-input>
            </td>
          </tr>

          <tr>
            <td>邮箱地址：</td>
            <td>
              <el-input v-model="user1.email"></el-input>
            </td>
          </tr>
          <tr>
            <td>地址：</td>
            <td>
              <el-input v-model="user1.address"></el-input>
            </td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateInfo">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改用户密码"
      :visible.sync="passwordDialogVisible"
      width="30%">
      <div>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="请输入旧密码" prop="oldPass">
            <el-input type="password" v-model="ruleForm.oldPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="请输入新密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>

    <el-descriptions class="margin-top" title="个人中心" :column="3"  border>
      <template slot="extra">
        <el-button type="primary" size="small" @click="dialogVisible=true">修改个人信息</el-button>
      </template>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        {{user.username}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          姓名
        </template>
        {{user.name}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-s-promotion"></i>
          性别
        </template>
        {{user.gender}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          手机号
        </template>
        {{user.phone}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          角色
        </template>
      <span v-if="user.role==='admin'">管理员</span>
      <span v-if="user.role!=='admin'">工作人员</span>

      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          部门
        </template>
        <el-tag size="small">{{user.deptName}}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building"></i>
          联系地址
        </template>
        {{user.address}}
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
  import { getInfo,update } from '../../api/login'


  export default {
    name: 'userInfo',
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass')
          }
          callback()
        }
      }
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      return {
        user: {},
        user1: {},
        dialogVisible: false,
        passwordDialogVisible: false,
        ruleForm: {
          oldPass: '',
          pass: '',
          checkPass: ''
        },
        rules: {
          oldPass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      this.initUser()
    },
    methods: {
      showUpdate() {
        this.dialogVisible = true
      },
      initUser() {

        getInfo().then(resp => {
          console.log(resp)
          if (resp) {
            this.user = resp.data
            this.user1 = Object.assign({}, this.user)
          }
        })
      },
      //更新操作员信息
      updateInfo() {
        update(this.user1).then(resp => {
          if (resp) {
            this.dialogVisible = false
            this.initUser()
          }
        })
      },
      updatePassword() {
        this.passwordDialogVisible = true
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.postRequest('/logout')
            window.sessionStorage.removeItem('user')
            window.sessionStorage.removeItem('tokenStr')
            this.$router.replace('/')
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
      }
    }
  }
</script>

<style scoped>

</style>
