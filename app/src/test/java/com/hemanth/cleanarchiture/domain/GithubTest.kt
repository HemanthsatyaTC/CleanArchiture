package com.hemanth.cleanarchiture.domain
//
//import android.app.Activity
//import android.content.Context
//import android.widget.Toast
//import androidx.navigation.NavHostController
//import com.google.android.gms.tasks.Task
//import com.google.common.base.Verify.verify
//import com.google.firebase.auth.AuthResult
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.OAuthProvider
//import io.mockk.Runs
//import io.mockk.every
//import io.mockk.just
//import io.mockk.mockk
//import io.mockk.mockkStatic
//import io.mockk.verify
//import org.junit.Before
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//
//class GithubTest{
//
//    private lateinit var mockAuth: FirebaseAuth
//    private lateinit var mockContext: Context
//    private lateinit var mockNavController: NavHostController
//    private lateinit var mockActivity: Activity
//    private lateinit var mockPendingAuthResult: Task<AuthResult>
//    private lateinit var mockAuthResult: AuthResult
//
//    @Before
//    fun setup() {
//        mockAuth = mockk()
//        mockContext = mockk()
//        mockNavController = mockk(relaxed = true) // Relaxed to avoid extra verification
//        mockActivity = mockk()
//        mockPendingAuthResult = mockk()
//        mockAuthResult = mockk()
//
//        // Mocking the context as an Activity
//        every { mockContext as Activity } returns mockActivity
//    }
//
//    @Test
//    fun `test github sign in success with pending result`() {
//        // Arrange
//        every { mockAuth.pendingAuthResult } returns mockPendingAuthResult
//        every { mockPendingAuthResult.addOnSuccessListener(any()) } answers {
//            firstArg<(AuthResult) -> Unit>().invoke(mockAuthResult)
//            mockPendingAuthResult
//        }
//        every { mockPendingAuthResult.addOnFailureListener(any()) } returns mockPendingAuthResult
//
//        // Act
//        github(mockAuth, mockContext, mockNavController)
//
//        // Assert: Check if navigation happens on success
//        verify { mockNavController.navigate("electronics") }
//    }
//
//    @Test
//    fun `test github sign in failure with pending result`() {
//        // Arrange
//        every { mockAuth.pendingAuthResult } returns mockPendingAuthResult
//        every { mockPendingAuthResult.addOnSuccessListener(any()) } returns mockPendingAuthResult
//        every { mockPendingAuthResult.addOnFailureListener(any()) } answers {
//            firstArg<(Exception) -> Unit>().invoke(Exception("Sign-in failed"))
//            mockPendingAuthResult
//        }
//
//        // Mock Toast
//        mockkStatic(Toast::class)
//        every { Toast.makeText(mockContext, "Failed to github", Toast.LENGTH_SHORT).show() } just Runs
//
//        // Act
//        github(mockAuth, mockContext, mockNavController)
//
//        // Assert: Check if failure toast is shown
//        verify { Toast.makeText(mockContext, "Failed to github", Toast.LENGTH_SHORT).show() }
//    }
//
//    @Test
//    fun `test github sign in success without pending result`() {
//        // Arrange
//        every { mockAuth.pendingAuthResult } returns null
//
//        val mockProvider = mockk<OAuthProvider.Builder>()
//        mockkStatic(OAuthProvider::class)
//        every { OAuthProvider.newBuilder("github.com") } returns mockProvider
//        every { mockProvider.addCustomParameter(any(), any()) } returns mockProvider
//        every { mockProvider.scopes(any()) } returns mockProvider
//        every { mockAuth.startActivityForSignInWithProvider(any(), any()) } returns mockPendingAuthResult
//
//        every { mockPendingAuthResult.addOnSuccessListener(any()) } answers {
//            firstArg<(AuthResult) -> Unit>().invoke(mockAuthResult)
//            mockPendingAuthResult
//        }
//        every { mockPendingAuthResult.addOnFailureListener(any()) } returns mockPendingAuthResult
//
//        // Act
//        github(mockAuth, mockContext, mockNavController)
//
//        // Assert: Check if navigation happens on success
//        verify { mockNavController.navigate("electronics") }
//    }
//
//    @Test
//    fun `test github sign in failure without pending result`() {
//        // Arrange
//        every { mockAuth.pendingAuthResult } returns null
//
//        val mockProvider = mockk<OAuthProvider.Builder>()
//        mockkStatic(OAuthProvider::class)
//        every { OAuthProvider.newBuilder("github.com") } returns mockProvider
//        every { mockProvider.addCustomParameter(any(), any()) } returns mockProvider
//        every { mockProvider.scopes(listOf("user:email")) } returns mockProvider
//        every { mockAuth.startActivityForSignInWithProvider(any(), any()) } returns mockPendingAuthResult
//
//        every { mockPendingAuthResult.addOnSuccessListener(any()) } returns mockPendingAuthResult
//        every { mockPendingAuthResult.addOnFailureListener(any()) } answers {
//            firstArg<(Exception) -> Unit>().invoke(Exception("Sign-in failed"))
//            mockPendingAuthResult
//        }
//
//        // Mock Toast
//        mockkStatic(Toast::class)
//        every { Toast.makeText(mockContext, "Failed to github", Toast.LENGTH_SHORT).show() } just Runs
//
//        // Act
//        github(mockAuth, mockContext, mockNavController)
//
//        // Assert: Check if failure toast is shown
//        verify { Toast.makeText(mockContext, "Failed to github", Toast.LENGTH_SHORT).show() }
//    }
//}