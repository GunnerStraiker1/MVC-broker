import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { AppRegistry, Button } from 'react-native';
import { StackNavigator } from 'react-navigation';

/*class HomeScreen extends React.Component {
    static navigationOptions = {
        title: 'Welcome'
    };
    render() {
        return <Text>Hello, Navigation!</Text>;
    }
}*/

class HomeScreen extends React.Component {
    static navigationOptions = {
        title: 'Welcome',
    };
    render() {
        const { navigate } = this.props.navigation;
        return (
            <View>
                <Text>Hello, Chat App!</Text>
                <Button
                    onPress={() => navigate('Chat')}
                    title="Chat with Lucy"
                />
            </View>
        );
    }
}


class ChatScreen extends React.Component {
    static navigationOptions = {
        title: 'Chat with Lucy',
    };
    render() {
        return (
            <View>
                <Text>Chat with Lucy</Text>
            </View>
        );
    }
}


/*const SimpleApp = StackNavigator({
    Home: { screen: HomeScreen }
});*/

export const SimpleApp = StackNavigator({
    Home: { screen: HomeScreen },
    Chat: {
        screen: ChatScreen,
        navigationOptions: {
            title: 'MyScreen',
            headerLeft: null
        },
        /*navigationOptions: {
            title: "FirstPage",
            header: {
                left: null,
            }
        },*/
    },
});

export default class App extends React.Component {
    render() {
        return <SimpleApp />;
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center'
    }
});



