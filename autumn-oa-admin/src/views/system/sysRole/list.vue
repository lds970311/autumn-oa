<template>
  <div class="app-container">
    <!--查询表单-->
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <!-- 工具条 -->
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色名称">
              <el-input v-model="searchObj.roleName" style="width: 100%" placeholder="角色名称"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" :loading="listLoading" @click="fetchData()">搜索
          </el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
        <div class="tools-div">
          <el-button type="success" icon="el-icon-plus" size="mini" @click="addRole">添 加</el-button>
          <el-button class="btn-add" size="mini" @click="batchRemove">批量删除</el-button>
        </div>
      </el-form>
    </div>

    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%;margin-top: 10px;"
      @selection-change="handleSelectionChange"
    >

      <el-table-column type="selection"/>

      <el-table-column
        label="序号"
        width="70"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="roleName" label="角色名称"/>
      <el-table-column prop="roleCode" label="角色编码"/>
      <el-table-column prop="createTime" label="创建时间" width="160"/>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" title="修改" @click="edit(scope.row.id)"/>
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            title="删除"
            @click="removeDataById(scope.row.id)"
          />
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />

    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="sysRole" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="角色编码">
          <el-input v-model="sysRole.roleCode"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" icon="el-icon-refresh-right" @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" size="small" @click="saveOrUpdate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { batchRemove, getById, getPageList, removeById, save, update } from '@/api/system/sysRole'

export default {
  name: 'List',
  data() {
    return {
      list: [], // 列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 2, // 每页记录数
      searchObj: {
        roleName: '',
        roleCode: ''
      }, // 查询条件
      multipleSelection: [], // 批量删除选中的记录列表,
      listLoading: false,
      dialogVisible: false, // 添加对话框可见
      sysRole: {
        id: '',
        roleName: '',
        roleCode: ''
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData(current = 1) {
      this.page = current
      this.listLoading = true
      // 调用api
      getPageList(this.page, this.limit, this.searchObj).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      }).catch(err => {
        console.log(err)
      })
    },
    resetData() {
      this.searchObj.roleName = ''
    },
    removeDataById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return removeById(id)
      }).then((response) => {
        this.fetchData(this.page)
        this.$message.success(response.message || '删除成功')
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    edit(id) {
      this.dialogVisible = true
      this.fetchRoleById(id)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    addRole() {
      this.dialogVisible = true
    },
    async fetchRoleById(id) {
      try {
        const result = await getById(id)
        this.sysRole.id = result.data.id
        this.sysRole.roleCode = result.data.roleCode
        this.sysRole.roleName = result.data.roleName
      } catch (e) {
        console.log(e)
      }
    },

    saveOrUpdate() {
      if (!this.sysRole.id) {
        save(this.sysRole)
          .then(response => {
            this.$message.success(response.message || '操作成功')
            this.dialogVisible = false
            this.fetchData(this.page)
          })
      } else {
        update(this.sysRole)
          .then(response => {
            this.$message.success(response.message || '操作成功')
            this.dialogVisible = false
            this.fetchData(this.page)
          })
      }
    },
    batchRemove() {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('请选择要删除的记录！')
        return
      }
      console.log(this.multipleSelection)
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 点击确定，远程调用ajax
        // 遍历selection，将id取出放入id列表
        const idList = []
        this.multipleSelection.forEach(item => {
          idList.push(item.id)
        })
        // 调用api
        return batchRemove(idList)
      }).then((response) => {
        this.fetchData()
        this.$message.success(response.message)
      }).catch(err => {
        this.$message.error(err.message || '已取消删除')
      })
    }
  }
}
</script>

<style scoped lang="scss">

.tools-div {
  text-align: right;
}
</style>
