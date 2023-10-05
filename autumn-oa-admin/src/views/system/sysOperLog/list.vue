<template>
  <div class="log">

  </div>
</template>

<script>
import sysOperLog from '@/api/system/sysOperLog'

export default {
  name: 'SysOperLog',
  methods: {
    getLogs() {
      sysOperLog.getAllLogs()
    }
  },
  data() {
    return {
      logList: []
    }
  },
  mounted() {
    this.getLogs()
  }
}
</script>


<style scoped lang="scss">

</style>
