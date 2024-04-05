import { library } from '@fortawesome/fontawesome-svg-core'
import { faHeart, faThumbsDown } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import './index.css'

import App from './App.vue'
import router from './router'

library.add(faHeart, faThumbsDown)

const app = createApp(App)

app.component('font-awesome-icon', FontAwesomeIcon)
app.use(createPinia())
app.use(router)

app.mount('#app')
