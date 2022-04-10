import { createApp } from 'vue'
import App from './App.vue'
import router from "./router/index.js";
import store from "./store/index.js";
// import MyPlugin from "./plugins/history.js";
import {
    CountDown,
    Search,
    Calendar,
    Progress,
    Slider,
    Form,
    Field,
    Button,
    Divider,
    Swipe,
    Cell,
    CellGroup,
    SwipeCell,
    SwipeItem,
    Tabbar,
    TabbarItem,
    Lazyload,
    Col,
    Row,
    Stepper,
    Switch,
    RadioGroup,
    Radio,
    Picker,
    Popup,
    Card,
    Tag,
    IndexBar,
    IndexAnchor,
    Badge,
    Icon,
    Image as VanImage,
    NavBar,
    Toast as VanToast,
    Checkbox,
    CheckboxGroup

} from 'vant';


const app = createApp(App)
// Vant
app.use(Checkbox);
app.use(CheckboxGroup);
app.use(VanToast);
app.use(CountDown);
app.use(Search);
app.use(Calendar);
app.use(Progress);
app.use(Icon);
app.use(NavBar);
app.use(VanImage);
app.use(Badge);
app.use(Field);
app.use(IndexBar);
app.use(IndexAnchor);
app.use(Button);
app.use(Form);
app.use(Divider);
app.use(Cell);
app.use(CellGroup);
app.use(Col);
app.use(Row);
app.use(Stepper);
app.use(RadioGroup);
app.use(Radio);
app.use(Switch);
app.use(Swipe);
app.use(SwipeItem);
app.use(SwipeCell);
app.use(Tabbar);
app.use(TabbarItem);
app.use(Picker);
app.use(Popup);
app.use(Lazyload);
app.use(Card);
app.use(Tag);
app.use(Slider)
app.use(store)
app.use(router)

// app.use(MyPlugin)

app.mount('#app')
