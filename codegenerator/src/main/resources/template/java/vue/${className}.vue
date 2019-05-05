<#assign className = table.className>
<#assign classNameLower = table.classNameLower>

<template>
    <div class="app-container">
        <section style="margin-bottom: 20px;">
            <el-button @click="openAdd" type="primary">新增${table.tableRemark}</el-button>
        </section>
        <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit
                  highlight-current-row>

            <#list table.columns as column>
                <#if !column.baseField>
                <el-table-column align="center" label='${column.remarks}'>
                    <template slot-scope="scope">
                        {{scope.row.${column.columnNameLower}}}
                    </template>
                </el-table-column>
                </#if>
            </#list>
        </el-table>
        <!--新增修改 -->
        <el-dialog :title="dialog.dialogTitle" :visible.sync="dialog.dialogFormVisible">
            <el-form :model="form" ref="${classNameLower}Form" label-width="80px" :rules="rules">

                <#list table.columns as column>
                    <#if !column.baseField>
                    <el-form-item label="${column.remarks}" prop="${column.columnNameLower}">
                        <el-input v-model="form.${column.columnNameLower}"></el-input>
                    </el-form-item>
                    </#if>
                </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialog.dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {${classNameLower}Client} from '@/api/clients'

    export default {
        data() {
            return {
                dialog: {
                    dialogEdit: false,
                    dialogTitle: '新增用户',
                    dialogFormVisible: false,
                },
                list: null,
                listLoading: true,
                query: {},
                form: {

                },
                rules: {
                }
            }
        },
        filters: {

        },
        created() {
            this.getList()
        },
        methods: {
            openAdd() {
                this.dialog.dialogFormVisible = true;
                this.dialog.dialogEdit = false;
                this.dialog.dialogTitle = '新增${table.tableRemark}';
                this.form = {};
            },
            openEdit(row) {
                this.dialog.dialogEdit = true;
                this.dialog.dialogFormVisible = true;
                this.dialog.dialogTitle = '修改${table.tableRemark}';
                this.form = row;
            },
            save() {
                this.$refs.${classNameLower}Form.validate((valid) => {
                    if (valid) {
                        ${classNameLower}Client.saveOrUpdate(this.form).then(res => {
                            if (res && res.data) {
                                this.$message('保存成功');
                                this.getList();
                            } else {
                                this.$message.error('保存失败');
                            }
                            this.dialog.dialogFormVisible = false;
                        })
                    }
                });
            },
            getList() {
                this.listLoading = true
                ${classNameLower}Client.loadPageInfo(this.query).then(res => {
                    this.list = res.data.list
                }).finally(() => this.listLoading = false);
            }
        }
    }
</script>