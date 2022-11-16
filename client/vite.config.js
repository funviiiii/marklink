import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

export default defineConfig({
    proxy: {
        '/api': {
            target: 'http://localhost:45678/personnel',  //你要跨域访问的网址
            changeOrigin: true,   // 允许跨域
            rewrite: (path) => path.replace(/^\/api/, '') // 重写路径把路径变成空字符
        }
    },
    plugins: [vue(),
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver()],
        }),],
    server: {
        port: 56789,
        opener: 'chrome',
        proxy: {
            '/api': {
                target: 'http://localhost:45678/personnel',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')
            }
        }
    },
})
