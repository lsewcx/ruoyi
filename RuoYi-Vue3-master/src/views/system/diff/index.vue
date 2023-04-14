<template>
    <code-diff :old-string="this.get()" :new-string="this.get1()" output-format="side-by-side" />

</template>

<script>
    export default {
        name: "diff",
        data() {
            return {
                string1: "",
                string2: "",
            }
        },
        created() {
            this.get()
        },
        methods: {
            get() {
                this.string1 = "12313131"
                return this.string1
            },
            get1() {
                this.string2 = "12313131"
                return this.string2
            }

        },
    }
</script>