<template>
    <div>
        <span>字体大小( ft-* ) ：</span>
        <div>
            <span class="ft-12 margin-right-20">12px</span>
            <span class="ft-18 margin-right-20">18px</span>
            <span class="ft-25 margin-right-20">25px</span>
            <span class="ft-40 margin-right-20">40px</span>
            <span class="ft-50 margin-right-20">50px</span>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">字体粗细( fw-* ) ：</span>
        <div class="ft-13">
            <span class="fw-1 margin-right-20">字体粗细1</span>
            <span class="fw-3 margin-right-20">字体粗细3</span>
            <span class="fw-5 margin-right-20">字体粗细5</span>
            <span class="fw-7 margin-right-20">字体粗细7</span>
            <span class="fw-9 margin-right-20">字体粗细9</span>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">文字颜色( text-* )：</span>
        <div class="ft-13">
            <span class="text-red margin-right-20">嫣红(red)</span>
            <span class="text-orange margin-right-20">桔橙(orange)</span>
            <span class="text-yellow margin-right-20">明黄(yellow)</span>
            <span class="text-olive margin-right-20">橄榄(olive)</span>
            <span class="text-green margin-right-20">森绿(green)</span>
            <span class="text-cyan margin-right-20">天青(cyan)</span>
            <span class="text-blue margin-right-20">海蓝(blue)</span>
            <span class="text-purple margin-right-20">姹紫(purple)</span>
            <span class="text-mauve margin-right-20">木槿(mauve)</span>
            <span class="text-pink margin-right-20">桃粉(pink)</span>
            <span class="text-brown margin-right-20">棕褐(brown)</span>
            <span class="text-greys margin-right-20">玄灰(greys)</span>
            <span class="text-gray margin-right-20">草灰(gray)</span>
            <span class="text-black margin-right-20">墨黑(black)</span>
            <span class="text-white margin-right-20">雅白(white)</span>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">快捷字体相关预定义样式 : </span>
        <div class="ft-13 text-cyan">
            <span class="i-line-through margin-right-20">INFRA(i-line-through)</span>
            <span class="i-italic margin-right-20">斜体(i-italic)</span>
            <span class="i-indent margin-right-20">缩进(i-indent)</span>
            <span class="i-text-Abc margin-right-20">infra(大写字母开头)(i-text-Abc)</span>
            <span class="i-text-ABC margin-right-20">infra(全部大写字母)(i-text-ABC)</span>
            <span class="i-text-abc margin-right-20">INFRA(全部小写字母)(i-text-abc)</span>
            <span class="i-text-price margin-right-20">20(前面加￥符号)(i-text-price)</span>
            <span class="i-text-price en margin-right-20">20(前面加$符号)(i-text-price en)</span>
            <span class="i-text-bold margin-right-20">粗体(i-text-bold)</span>
            <span class="i-text-center margin-right-20">文本居中(i-text-center)</span>
            <span class="i-text-left margin-right-20">文本居左(i-text-left)</span>
            <span class="i-text-right margin-right-20">文本居右(i-text-right)</span>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">边距( *-*-* ): </span>
        <div class="ft-13 text-cyan">
            <span class="margin-20">margin-20</span>
            <span class="padding-20">padding-20</span>
            <span class="border-20">border-20</span>
            <span class="margin-left-20">margin-left-20</span>
            <span class="padding-left-20">padding-right-20</span>
            <span class="margin-left-20">...</span>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">行高( i-lh-* ): </span>
        <div class="ft-13 text-cyan">
            <span class="i-lh-base margin-right-20">基础行高（i-lh-base）</span>
            <span class="i-lh-40 margin-right-20">动态行高(px)（i-lh-40）</span>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">背景颜色( bg-* ): </span>
        <div class="ft-13 flex-row-center">
            <div class="bg-red padding-20 margin-10"></div>
            <div class="bg-orange padding-20 margin-10"></div>
            <div class="bg-yellow padding-20 margin-10"></div>
            <div class="bg-olive padding-20 margin-10"></div>
            <div class="bg-green padding-20 margin-10"></div>
            <div class="bg-purple padding-20 margin-10"></div>
            <div class="bg-cyan padding-20 margin-10"></div>
            <div class="bg-blue padding-20 margin-10"></div>
            <div class="bg-mauve padding-20 margin-10"></div>
            <div class="bg-pink padding-20 margin-10"></div>
            <div class="bg-brown padding-20 margin-10"></div>
            <div class="bg-greys padding-20 margin-10"></div>
            <div class="bg-gray padding-20 margin-10"></div>
            <div class="bg-black padding-20 margin-10"></div>
            <div class="bg-white padding-20 margin-10"></div>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">flex比例布局:</span>
        <div class="flex">
            <div class="flex-sub bg-olive padding-5 margin-10">1 flex-sub</div>
            <div class="flex-sub bg-olive padding-5 margin-10">1 flex-sub</div>
        </div>
        <div class="flex">
            <div class="flex-sub bg-olive padding-5 margin-10">1 flex-sub</div>
            <div class="flex-twice bg-olive padding-5 margin-10">2 flex-twice</div>
        </div>
        <div class="flex">
            <div class="flex-sub bg-olive padding-5 margin-10">1 flex-sub</div>
            <div class="flex-twice bg-olive padding-5 margin-10">2 flex-twice</div>
            <div class="flex-treble bg-olive padding-5 margin-10">3 flex-treble</div>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">flex水平对齐:</span>
        <div class="justify-start">
            <div class="bg-green padding-10 margin-10">justify-start</div>
            <div class="bg-green padding-10 margin-10">justify-start</div>
        </div>
        <div class="justify-end">
            <div class="bg-green padding-10 margin-10">justify-end</div>
            <div class="bg-green padding-10 margin-10">justify-end</div>
        </div>
        <div class="justify-center">
            <div class="bg-green padding-10 margin-10">justify-center</div>
            <div class="bg-green padding-10 margin-10">justify-center</div>
        </div>
        <div class="justify-between">
            <div class="bg-green padding-10 margin-10">justify-between</div>
            <div class="bg-green padding-10 margin-10">justify-between</div>
        </div>
        <div class="justify-around">
            <div class="bg-green padding-10 margin-10">justify-around</div>
            <div class="bg-green padding-10 margin-10">justify-around</div>
        </div>
        <el-divider></el-divider>
        <span class="i-lh-40">flex垂直对齐:</span>
        <div class="flex-row">
            <div class="align-start margin-right-50">
                <div class="bg-cyan padding-20 margin-10">INFRA</div>
                <div class="bg-cyan padding-10 margin-10">align-start</div>
            </div>
            <div class="align-end margin-right-50">
                <div class="bg-cyan padding-20 margin-10">INFRA</div>
                <div class="bg-cyan padding-10 margin-10">align-end</div>
            </div>
            <div class="align-center">
                <div class="bg-cyan padding-20 margin-10">INFRA</div>
                <div class="bg-cyan padding-10 margin-10">align-center</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
    }
</script>

<style scoped>

</style>